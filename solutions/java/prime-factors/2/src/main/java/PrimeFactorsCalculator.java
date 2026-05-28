import java.util.ArrayList;
import java.util.List;
public class PrimeFactorsCalculator {
    public List<Long> calculatePrimeFactorsOf(Long numLong) {
        List<Long> primeFactors = new ArrayList<>();
        if (numLong <= 1L) {
            return primeFactors;
        }
        long number = 2L;
        while (numLong > 1) {
            if (isPrime(number)) {
                if (numLong % number == 0) {
                    numLong /= number;
                    primeFactors.add(number);
                } else {
                    number++;
                }
            } else {
                number++;
            }
        }
        return primeFactors;
    }
    private boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        for (long i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}