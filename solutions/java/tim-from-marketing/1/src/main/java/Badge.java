public class Badge {
    public String print(Integer id, String name, String department) {
        StringBuilder sb = new StringBuilder();
        if (id != null){
            sb.append("[").append(id).append("]").append(" - ");
        }
        if(name != null){
           sb.append(name).append(" - ");
        }
        if(department != null){
           sb.append(department.toUpperCase());
        }else {
            sb.append("OWNER");
        }
        return sb.toString();
    }
}