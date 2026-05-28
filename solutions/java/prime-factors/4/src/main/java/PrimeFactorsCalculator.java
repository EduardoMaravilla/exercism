import java.util.ArrayList;
import java.util.List;
public class PrimeFactorsCalculator {
    public List<Long> calculatePrimeFactorsOf(Long numLong) {
        List<Long> primeFactors = new ArrayList<>();
        if (numLong <= 1L) {
            return primeFactors;
        }
        long number = 2L;
        while (numLong > 1L) {
            while(numLong % number == 0){
                primeFactors.add(number);
                numLong /= number;
            }
            number++;
            if(number * number > numLong && numLong>1L){
                    primeFactors.add(numLong);
                    break;
            }
        }
        return primeFactors;
    }
}