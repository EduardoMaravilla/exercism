import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HandshakeCalculator {

    List<Signal> calculateHandshake(int number) {
        List<Signal> handshakeSignals = new ArrayList<>();

        // Bit masks for each signal
        final int WINK_MASK = 0b00001;
        final int DOUBLE_BLINK_MASK = 0b00010;
        final int CLOSE_YOUR_EYES_MASK = 0b00100;
        final int JUMP_MASK = 0b01000;
        final int REVERSE_MASK = 0b10000;

        // Add signals based on bit masks
        if ((number & WINK_MASK) == WINK_MASK) {
            handshakeSignals.add(Signal.WINK);
        }
        if ((number & DOUBLE_BLINK_MASK) == DOUBLE_BLINK_MASK) {
            handshakeSignals.add(Signal.DOUBLE_BLINK);
        }
        if ((number & CLOSE_YOUR_EYES_MASK) == CLOSE_YOUR_EYES_MASK) {
            handshakeSignals.add(Signal.CLOSE_YOUR_EYES);
        }
        if ((number & JUMP_MASK) == JUMP_MASK) {
            handshakeSignals.add(Signal.JUMP);
        }

        // Check if the reverse bit is set
        if ((number & REVERSE_MASK) == REVERSE_MASK) {
            Collections.reverse(handshakeSignals);
        }

        return handshakeSignals;
    }
}    