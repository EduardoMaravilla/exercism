import java.util.List;
import java.util.ArrayList;

class Series {
    private String string;
    Series(String string) {
        this.string=string;
       
    }

    List<String> slices(int num) {
        List<String> lista = new ArrayList<>();
        if(num<=0){
          throw new IllegalArgumentException("Slice size is too small.");  
        }
        if(num>string.length()){
            throw new IllegalArgumentException("Slice size is too big.");
        }
         for(int i=0;i<=(string.length()-num);i++){
             String nueva=string.substring(i,i+num);
             lista.add(nueva);
         }
        
        return lista;
    }
}
