import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestApi {
    private final List<User> users;
    private String lender = "";
    private String borrower = "";
    private double amount = 0.0;

    public RestApi(User... users) {
        this.users = new ArrayList<>(List.of(users));
    }

    public String get(String url) {
        JSONArray jsonArray = new JSONArray();
        if (users != null) for (User user : users) jsonArray.put(user.name());
        return new JSONObject().put("users", jsonArray).toString();
    }

    public String get(String url, JSONObject payload) {
        JSONArray usersArray = payload.getJSONArray("users");
        if (url.equals("/users")) return jsonObjectUsers(usersArray).toString();
        return "[]";
    }

    public String post(String url, JSONObject payload) {
        if (url.equals("/add")) {
            String name = payload.getString("user");
            User user = User.builder().setName(name).build();
            if (!users.contains(user)) {
                users.add(user);
                return jsonObjectUser(user).toString();
            }
        } else if (url.equals("/iou")) {
            this.lender = payload.getString("lender");
            this.borrower = payload.getString("borrower");
            this.amount = payload.getDouble("amount");
            if (this.lender.compareTo(this.borrower) < 0) return jsonObjectUsers(new JSONArray().put(this.lender).put(this.borrower)).toString();
            else return jsonObjectUsers(new JSONArray().put(this.borrower).put(this.lender)).toString();            
        }
        return "[]";
    }

    private JSONObject jsonObjectUser(User user) {
        List<Iou> newOwes = new ArrayList<>();
        List<Iou> newOwesBy = new ArrayList<>();
        JSONObject jsonOwes = new JSONObject();
        JSONObject jsonOwesBy = new JSONObject();
        double oweTotalAmount = 0.0;
        double oweByTotalAmount = 0.0;
        if (lender.length() > 0 && user.name().equals(lender)) {
            List<List<Iou>> updateList = updateLenderOrBorrower(this.borrower,this.amount,user.owes(),user.owedBy());
            newOwes = updateList.get(0);
            newOwesBy = updateList.get(1);
        }
        if (borrower.length() > 0 && user.name().equals(borrower)) {
            List<List<Iou>> updateList = updateLenderOrBorrower(this.lender,this.amount,user.owedBy(),user.owes());
            newOwes = updateList.get(1);
            newOwesBy = updateList.get(0);
        }
        if (!newOwes.isEmpty()) {
            for (Iou owe : newOwes) {
                jsonOwes.put(owe.name, owe.amount);
                oweTotalAmount += owe.amount;
            }
        }
        if (!newOwesBy.isEmpty()) {
            for (Iou oweBy : newOwesBy) {
                jsonOwesBy.put(oweBy.name, oweBy.amount);
                oweByTotalAmount += oweBy.amount;
            }
        }
        double balance = oweByTotalAmount - oweTotalAmount;
        return new JSONObject().put("name", user.name()).put("owes", jsonOwes).put("owedBy", jsonOwesBy).put("balance", balance);
    }

    private JSONObject jsonObjectUsers(JSONArray jsonArray) {
        JSONArray jsonArrayUsers = new JSONArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            String name = jsonArray.getString(i);
            for (User user : users) {
                if (user.name().equals(name)) jsonArrayUsers.put(jsonObjectUser(user));
            }
        }
        return new JSONObject().put("users", jsonArrayUsers);
    }

    List<List<Iou>> updateLenderOrBorrower(String lendeOrBorrowed,double amount ,List<Iou> owes,List<Iou> owesBy) {
        List<Iou> newOwes = new ArrayList<>();
        List<Iou> newOwesBy = new ArrayList<>();
        double newAmount = 0.0;
        boolean hasBorrower = false;
        boolean hasPay = false;
        for (Iou owe : owes) {
            if (owe.name.equals(lendeOrBorrowed)) {
                newAmount = owe.amount - amount;
                if (newAmount > 0.0) {
                    hasPay = true;
                    newOwes.add(new Iou(owe.name, newAmount));
                } else if (newAmount == 0.0) {
                    hasPay = true;
                }
            } else {
                newOwes.add(new Iou(owe.name, owe.amount));
            }
        }
        for (Iou oweBy : owesBy) {
            if (oweBy.name.equals(lendeOrBorrowed)) {
                hasBorrower = true;
                newAmount = oweBy.amount + amount;
                newOwesBy.add(new Iou(oweBy.name, newAmount));
            } else {
                newOwesBy.add(new Iou(oweBy.name, oweBy.amount));
            }
        }
        if (newAmount < 0.0) newOwesBy.add(new Iou(lendeOrBorrowed, newAmount * -1.0));
        else if (!hasBorrower && !hasPay) newOwesBy.add(new Iou(lendeOrBorrowed, amount));        
        return new ArrayList<>(List.of(newOwes,newOwesBy));
    }
}