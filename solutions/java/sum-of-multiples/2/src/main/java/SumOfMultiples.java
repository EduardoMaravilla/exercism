import java.util.Set;
import java.util.HashSet;

class SumOfMultiples {
    private final Set<Integer> allNumbers;

    SumOfMultiples(int number, int[] set) {
        allNumbers = new HashSet<>();
        for (int val : set) {
            if (val == 0) continue; // omitir valores cero
            for (int j = val; j < number; j += val) {
                allNumbers.add(j);
            }
        }
    }
    int getSum() {        
        return allNumbers.stream().reduce(Integer::sum).orElse(0);
    }
}