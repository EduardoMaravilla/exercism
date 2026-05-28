import java.util.List;
import java.util.ArrayList;
class Sieve {
    private final List<Integer> numPrime;
    Sieve(int maxPrime) {
        this.numPrime = new ArrayList<>();
        if (maxPrime > 1) {
            for (int i = 2; i <= maxPrime; i++) {
                numPrime.add(i);
            }
        }
    }
    List<Integer> getPrimes() {
        List<Integer> primos = new ArrayList<>();
        for (Integer num : numPrime) {
            boolean esPrimo = true;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    esPrimo = false;
                    break;
                }
            }
            if (esPrimo) {
                primos.add(num);
            }
        }
        return primos;
    }
}