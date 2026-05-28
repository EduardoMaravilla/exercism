import java.util.ArrayList;
import java.util.List;

class Sieve {
    private final int max;

    Sieve(int maxPrime) {
        this.max = maxPrime;
    }
    List<Integer> getPrimes() {
        boolean[] isComposite = new boolean[max + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= max; i++) {
            if (!isComposite[i]) {
                primes.add(i);
                for (int j = i * 2; j <= max; j += i) {
                    isComposite[j] = true;
                }
            }
        }
        return primes;
    }
}