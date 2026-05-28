import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class HandshakeCalculator {

    List<Signal> calculateHandshake(int number) {
        int control = 1;
        List<Signal> Apreton = new ArrayList<>();
        String binario = Integer.toBinaryString(number);

        for (int i = (binario.length() - 1); i >= 0; i--) {
            char c = binario.charAt(i);
            if (c == '1' && control == 1) {
                Apreton.add(Signal.WINK);
            } else if (c == '1' && control == 2) {
                Apreton.add(Signal.DOUBLE_BLINK);
            } else if (c == '1' && control == 3) {
                Apreton.add(Signal.CLOSE_YOUR_EYES);
            } else if (c == '1' && control == 4) {
                Apreton.add(Signal.JUMP);
            } else if (c == '1' && control == 5) {
                Collections.reverse(Apreton);
            }
            control++;
        }
        return Apreton;
    }
}