import java.util.Collections;
import java.util.Arrays;
class ReverseString {

    String reverse(String inputString) {
        String[] reverseText = inputString.split("");
        Collections.reverse(Arrays.asList(reverseText));
        return String.join("", reverseText);
    }

}