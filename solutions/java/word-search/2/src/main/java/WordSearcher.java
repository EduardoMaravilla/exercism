import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


class WordSearcher {
    Map<String, Optional<WordLocation>> search(final Set<String> words, final char[][] grid) {
        Map<String, Optional<WordLocation>> busqueda=new HashMap<>();
        List<String> Horizontal=new ArrayList<>();
        List<String> Vertical=new ArrayList<>();
        List<String> inclinado=new ArrayList<>();
        int fila=grid.length;
        int columna=grid[0].length;        
        
        for (int i = 0; i < fila; i++) {
            StringBuilder cadena=new StringBuilder();
            for (int j = 0; j < columna; j++) {
                cadena.append(grid[i][j]);
            }
            Horizontal.add(cadena.toString());
        }  
        for (int i = 0; i < columna; i++) {
            StringBuilder cadena=new StringBuilder();
            for (int j = 0; j < fila; j++) {
                cadena.append(grid[j][i]);
            }
            Vertical.add(cadena.toString());
        }
        for (String word : words) {
            String revword="";
            for (int i =(word.length()-1); i>=0; i--) {
                revword += word.charAt(i);
            }
            boolean palabra=false;
            for (int i = 0; i < Horizontal.size(); i++) {
                if(Horizontal.get(i).contains(word)){
                    palabra=true;
                    int pos1=Horizontal.get(i).indexOf(word);
                    int pos2=word.length();
                    busqueda.put(word,Optional.of(new WordLocation(new Pair(pos1+1, i+1),new Pair((pos1+pos2), i+1))));

                }else if(Horizontal.get(i).contains(revword)){
                    palabra=true;
                    int pos1=Horizontal.get(i).indexOf(revword);
                    int pos2=revword.length();
                    busqueda.put(word,Optional.of(new WordLocation(new Pair((pos1+pos2), i+1),new Pair(pos1+1, i+1))));
                }
            }
            if(palabra==false){
                for (int i = 0; i < Vertical.size(); i++) {
                    if(Vertical.get(i).contains(word)){
                        palabra=true;
                        int pos1=Vertical.get(i).indexOf(word);
                        int pos2=word.length();
                        busqueda.put(word,Optional.of(new WordLocation(new Pair(i+1,pos1+1),new Pair(i+1,(pos1+pos2)))));                         
                    }else if(Vertical.get(i).contains(revword)){
                        palabra=true;
                        int pos1=Vertical.get(i).indexOf(revword);
                        int pos2=revword.length();
                       busqueda.put(word,Optional.of(new WordLocation(new Pair(i+1,(pos1+pos2)),new Pair( i+1,pos1+1))));
                    }
                }
            }
            if(palabra==false){                
                       for (int i = 0; i < fila; i++) {
                      for (int j = 0; j < columna; j++) {            
            if (searchDiagonal(word, i, j, 1, 1, grid)) {
                busqueda.put(word, Optional.of(new WordLocation(new Pair( j+1,i+1), new Pair( j + word.length(),i + word.length()))));
                palabra=true;
                
            }            
            if (searchDiagonal(word, i, j, -1, -1, grid)) {
                busqueda.put(word, Optional.of(new WordLocation(new Pair(j+1,i+1), new Pair( j - word.length() + 2,i - word.length() + 2))));
                palabra=true;
                
            }            
            if (searchDiagonal(word, i, j, 1, -1, grid)) {
                palabra=true;
                busqueda.put(word, Optional.of(new WordLocation(new Pair(j+1,i+1), new Pair(j - word.length() + 2,i + word.length()))));
               
            }            
            if (searchDiagonal(word, i, j, -1, 1, grid)) {
                palabra=true;
                busqueda.put(word, Optional.of(new WordLocation(new Pair(j+1,i+1), new Pair( j + word.length(),i - word.length() + 2))));
            }              
        }
           if(palabra==true){
               break;
           }                
       }
            }
            if(palabra==false){
               busqueda.put(word,Optional.empty());
            }

        }        
        return busqueda;   
    }
    private  boolean searchDiagonal(String word, int row, int col, int rowInc, int colInc, char[][] grid) {    
    int len = word.length();
    if (row + rowInc * (len - 1) >= grid.length || col + colInc * (len - 1) >= grid[0].length || row + rowInc * (len - 1) < 0 || col + colInc * (len - 1) < 0) {
        return false;
    }
    // Comprobar si la palabra está presente en diagonal
    for (int i = 0; i < len; i++) {
        if (grid[row + i * rowInc][col + i * colInc] != word.charAt(i)) {
            return false;
        }
    }
    return true;
}
}