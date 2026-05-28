import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Knapsack {
    int maximumValue(int weight, List<Item> items) {
        if(items.size()==0){
            return 0;
        }
        List <Integer> max=new ArrayList<>();
        int[][] itemMatrix= new int[items.size()][2];
        for (int i = 0; i < items.size(); i++) {
            itemMatrix[i][0] = items.get(i).weight;
            itemMatrix[i][1] = items.get(i).value;
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
                char character = combi.charAt(k);
                int numero = Integer.parseInt(String.valueOf(character));
                valor=valor+itemMatrix[k][1]*numero;
                peso=peso+itemMatrix[k][0]*numero;
            }
            if(peso<=weight){
                max.add(valor);
            }

        }
        max.sort(Collections.reverseOrder());
        return max.get(0);

    }
}