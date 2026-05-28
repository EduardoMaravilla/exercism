import java.util.List;
import java.util.ArrayList;

class Series {
    private String string;
    Series(String string) {
        if( string.isEmpty()) throw new IllegalArgumentException("series cannot be empty");
        this.string=string;       
    }
    List<String> slices(int num) {
        List<String> lista = new ArrayList<>();
        if(num <= 0) throw new IllegalArgumentException("slice length cannot be negative or zero");  
        if(num>string.length()) throw new IllegalArgumentException("slice length cannot be greater than series length");        
         for(int i=0;i<=(string.length()-num);i++){
             String nueva=string.substring(i,i+num);
             lista.add(nueva);
         }        
        return lista;
    }
}