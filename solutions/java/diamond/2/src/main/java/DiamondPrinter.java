import java.util.List;
import java.util.ArrayList;
class DiamondPrinter {
    List<String> printToList(char a) {
        List<String> diamond= new ArrayList<>();
        String abc="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int pos=abc.indexOf(a);
        int rep=pos+1;
        int control=pos*2+1;
        if (a == 'A') {
            diamond.add("A");
            return diamond;
        }
        for(int i=1; i<=rep;i++){
            StringBuilder sb= new StringBuilder();
            sb.append(" ".repeat(Math.max(0, control)));
            String letra=abc.substring(i-1,i);
            String nueva;
            if(i==1){
                nueva=sb.substring(0, rep - 1) + letra + sb.substring(rep);
                diamond.add(nueva);

            }else if(i<rep){
                nueva=sb.substring(0, rep - i) + letra + sb.substring(rep - i + 1, rep - 2 + i) + letra
                        + sb.substring(rep - 1 + i);
                diamond.add(nueva);

            }else {
                nueva = letra + sb.substring(1, control - 1) + letra;
                diamond.add(nueva);
            }
        }
        for (int i = rep - 1; i >= 1; i--) {
            diamond.add(diamond.get(i - 1));
        }
        return diamond;
    }
}