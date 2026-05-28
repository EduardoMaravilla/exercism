import java.util.Collections;
import java.util.Arrays;
class ReverseString {

    String reverse(String inputString) {
        String[] reversetext = inputString.split("");
        Collections.reverse(Arrays.asList(reversetext));
        String reversedText = String.join("", reversetext);
        return reversedText;
    }
  
}
