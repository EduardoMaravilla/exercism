import java.util.*;
public class Flattener {
    public List<Object> flatten(List<Object> list) {
        List<Object> result = new ArrayList<>();
        for (Object element : list) {
            if (element instanceof List) {
                List<Object> sublist = flatten((List<Object>) element);
                result.addAll(sublist);
            } else if (element != null) {
                result.add(element);
            }
        }
        return result;
    }
}