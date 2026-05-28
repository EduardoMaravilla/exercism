import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Etl {
    Map<String, Integer> transform(Map<Integer, List<String>> old) {
        Map<String, Integer> transform=new HashMap<>();
        for (Map.Entry<Integer, List<String>> entry : old.entrySet()) {
            Integer key = entry.getKey();
            List<String> val = entry.getValue();
            for (String string : val) {
                transform.put(string.toLowerCase(), key);
            }            
        }      
        return transform;
    }
}