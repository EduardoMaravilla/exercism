import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class SumOfMultiples {
    private List<Integer> allNumbers = new ArrayList<>();

    SumOfMultiples(int number, int[] set) {
        for (int i = 0; i < set.length; i++) {
            int val = set[i];
            if (val == 0) continue; // omitir valores cero
            for (int j = val; j < number; j += val) {
                allNumbers.add(j);
            }
        }
    }
    
    int getSum() {
        Set<Integer> uniqueNumbers = new HashSet<>(allNumbers);
        int sum = 0;
        for (int number : uniqueNumbers) {
            sum += number;     
        }
        return sum;
    }
}
