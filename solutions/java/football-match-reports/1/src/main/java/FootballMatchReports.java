public class FootballMatchReports {
    
    public static String onField(int shirtNum) {
        if(shirtNum<=0 || shirtNum>=12){
            throw new IllegalArgumentException("Numero incorrecto");
        }

        
        String lugar="";        
            
        switch (shirtNum) {
            case 1:
                lugar="goalie";
                break;
            case 2:
                lugar="left back";
                break;
            case 3:
            case 4:    
                lugar="center back";
                break;
            case 5:
                lugar="right back";
                break;
            case 6:
            case 7:
            case 8:    
                lugar="midfielder";
                break;
            case 9:
                lugar="left wing";
                break;
            case 10:
                lugar="striker";
                break;
            case 11:
                lugar="right wing";
                break;
            default:
                lugar="";
            break;
        }
        return lugar;
    }
}
