import java.util.ArrayList;
import java.util.List;

public class Tournament {

    private String table = "";
    public Tournament() {
    }

    public String printTable() {
        String table = "Team                           | MP |  W |  D |  L |  P\n";
        return table+this.table;
    }

    public void applyResults(String resultString) {
        List<String> tabla=new ArrayList<>();
        int mp = 0, w = 0, d = 0, l = 0, p = 0,p2=0;
        resultString = resultString.replace("\n", ";");
        String[] results = resultString.split(";");
        String[] teams = {"Allegoric Alaskans", "Blithering Badgers", "Courageous Californians", "Devastating Donkeys"};

        for (String team : teams) {
            for (int i = 0; i < results.length - 1; i++) {
                if (results[i].equals(team)) {
                    mp++;
                    if (i <= results.length - 3) {
                        switch (results[i + 2]) {
                            case "win" -> w++;
                            case "draw" -> d++;
                            case "loss" -> l++;
                            default -> {
                            }
                        }
                    }
                    if (i <= results.length - 1) {
                        switch (results[i + 1]) {
                            case "win" -> l++;
                            case "draw" -> d++;
                            case "loss" -> w++;
                            default -> {
                            }
                        }
                    }
                }
            }
            p = w * 3 + d;
            String table = "";
            if (mp > 0) {
                switch (team) {
                    case "Devastating Donkeys" ->
                            table = team + "            " + "|  " + mp + " |  " + w + " |  " + d + " |  " + l + " |  " + p + "\n";
                    case "Allegoric Alaskans", "Blithering Badgers" ->
                            table = team + "             " + "|  " + mp + " |  " + w + " |  " + d + " |  " + l + " |  " + p + "\n";
                    case "Courageous Californians" ->
                            table = team + "        " + "|  " + mp + " |  " + w + " |  " + d + " |  " + l + " |  " + p + "\n";
                    default -> {
                    }
                }
            }

            if(p>p2){
                tabla.add(0,table);
                p2=p;
            }else if(p2==p && p2!=0){
                tabla.add(1,table);
            }else{
                tabla.add(table);
            }
            w = 0;
            d = 0;
            l = 0;
            mp = 0;
        }
        this.table += String.join("",tabla);
    }
}