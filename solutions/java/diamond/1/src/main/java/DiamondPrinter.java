import java.util.List;
import java.util.ArrayList;

class DiamondPrinter {

    List<String> printToList(char a) {
        List<String> diamond= new ArrayList<>();
      String abc="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int pos=abc.indexOf(a); //Extrae la posicion de la letra en la cadena
        int rep=pos+1; //numero de repeticiones para el diamante
        int control=pos*2+1;
        if (a == 'A') {
            diamond.add("A");
            return diamond;
        }
        
        for(int i=1; i<=rep;i++){
          String cadena="";
            for(int j=1;j<=control;j++){
                cadena=cadena + " ";//Se crea una cadena de espacios
            }
            String letra=abc.substring(i-1,i);
            String nueva;
           if(i==1){
               nueva=cadena.substring(0, rep - 1) + letra + cadena.substring(rep);
               diamond.add(nueva);
               
           }else if(i<rep){
               nueva=cadena.substring(0, rep - i) + letra + cadena.substring(rep - i + 1, rep - 2 + i) + letra
                        + cadena.substring(rep - 1 + i);
               diamond.add(nueva);
               
           }else if(i==rep){
                nueva = letra + cadena.substring(1, control - 1) + letra;
                  diamond.add(nueva);
               }  
        }
        for (int i = rep - 1; i >= 1; i--) {
            diamond.add(diamond.get(i - 1));
            }
        return diamond;
    }

}
