import java.util.Random;
import java.util.Set;
import java.util.HashSet;

class Robot {
    private static Set<String> usedNames = new HashSet<>();
    private String Name;
    private int num;
    
    public Robot() {
        String newName;
        do {
            Random random = new Random();
            char le1 = (char)(random.nextInt(26) + 'A');
            char le2 = (char)(random.nextInt(26) + 'A');
            num = random.nextInt(1000);
            String Subcadena = String.format("%03d", num);
            newName = "" + le1 + le2 + Subcadena; 
        } while(usedNames.contains(newName));
        usedNames.add(newName);
        Name = newName;
    }
    
    public String getName() {
        return Name;
    }
    
    public void reset() {
        String newName2;
        do {
            Random random = new Random();
            char le1 = (char)(random.nextInt(26) + 'A');
            char le2 = (char)(random.nextInt(26) + 'A');
            num = random.nextInt(1000);
            String Subcadena = String.format("%03d", num);
            newName2 = "" + le1 + le2 + Subcadena; 
        } while(usedNames.contains(newName2));
        usedNames.add(newName2);
        Name = newName2;
    }
}
