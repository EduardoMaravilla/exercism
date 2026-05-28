import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;

public class Knapsack {
    int maximumValue(int Weight, List<Item> items) {
          if(items.size()==0){
             return 0;
         }
         List <Integer> maximo=new ArrayList<>();
        int[][] objetos= new int[items.size()][2];
        for (int i = 0; i < items.size(); i++) {
              objetos[i][0] = items.get(i).getWeight(); 
              objetos[i][1] = items.get(i).getValue(); 
                  }
        
        
        int numCombinaciones = (int) Math.pow(2, items.size());
        String[] resultados = new String[numCombinaciones];
        for (int i = 0; i < numCombinaciones; i++) {
        String binario = String.format("%" + items.size() + "s", Integer.toBinaryString(i)).replace(' ', '0');
        resultados[i] = binario;
          }
        for(int j=0;j<numCombinaciones;j++){
            int valor=0;
            int peso=0;
            String combi=resultados[j];
            for(int k=0;k<items.size();k++){                
                char Caracter = combi.charAt(k);
                int numero = Integer.parseInt(String.valueOf(Caracter));
                valor=valor+objetos[k][1]*numero;
                peso=peso+objetos[k][0]*numero;
            }
            if(peso<=Weight){
                maximo.add(valor);
            }
        
        }
        
        Collections.sort(maximo, Collections.reverseOrder());
      
        
            return maximo.get(0);
       
}
}
class Item {
    private int weight;
    private int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getValue() {
        return this.value;
    }

    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }
}
