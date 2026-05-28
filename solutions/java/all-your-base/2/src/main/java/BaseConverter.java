import java.util.ArrayList;
import java.util.List;
public class BaseConverter {
    private int fromBase;
    private int[] digits;
    public BaseConverter(int fromBase, int[] digits) {
        if (fromBase < 2) {
            throw new IllegalArgumentException("Bases must be at least 2.");
        }
        for (int digit : digits) {
            if (digit < 0) {
                throw new IllegalArgumentException("Digits may not be negative.");
            }
            if (digit >= fromBase) {
                throw new IllegalArgumentException("All digits must be strictly less than the base.");
            }
        }
        this.fromBase = fromBase;
        this.digits = digits;
    }
    public List<Integer> convertToBase(int toBase) {
        if (toBase < 2) {
            throw new IllegalArgumentException("Bases must be at least 2.");
        }        
        int decimalValue = 0;
        for (int digit : digits) {
            decimalValue = decimalValue * fromBase + digit;
        }        
        List<Integer> resultDigits = new ArrayList<>();
        while (decimalValue > 0) {
            resultDigits.add(0, decimalValue % toBase);
            decimalValue /= toBase;
        }        
        if (resultDigits.isEmpty()) {
            resultDigits.add(0);
        }        
        return resultDigits;
    }
}