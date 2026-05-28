import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class PalindromeCalculator {
    public SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int minFactor, int maxFactor) {
        if (minFactor > maxFactor) {
            throw new IllegalArgumentException("invalid input: min must be <= max");
        }

        SortedMap<Long, List<List<Integer>>> palindromes = new TreeMap<>();        
        for (long i = minFactor; i <= maxFactor; i++) {
            for (long j = i; j <= maxFactor; j++) {
                long product = i * j;
                if (isPalindrome(product)) {
                    List<Integer> factors = new ArrayList<>();
                    factors.add((int) i);
                    factors.add((int) j);
                    List<List<Integer>> existingFactors = palindromes.getOrDefault(product, new ArrayList<>());
                    existingFactors.add(factors);
                    palindromes.put(product, existingFactors);
                }
            }
        }
        
        return palindromes;
    }

    private boolean isPalindrome(long number) {
        String strNumber = Long.toString(number);
        String reversedStr = new StringBuilder(strNumber).reverse().toString();
        return strNumber.equals(reversedStr);
    }
}
