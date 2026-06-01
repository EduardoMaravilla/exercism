import java.util.ArrayList;
import java.util.List;

public class RailFenceCipher {
    private final int rails;

    public RailFenceCipher(int rails) {
        this.rails = rails;
    }

    public String getEncryptedData(String text) {
        if (rails <= 1) return text;

        List<StringBuilder> fence = new ArrayList<>();
        for (int i = 0; i < rails; i++) fence.add(new StringBuilder());

        int rail = 0;
        int direction = 1;

        for (char c : text.toCharArray()) {
            fence.get(rail).append(c);
            rail += direction;
            if (rail == 0 || rail == rails - 1) direction *= -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : fence) result.append(sb);
        return result.toString();
    }

    public String getDecryptedData(String encryptedText) {
        if (rails <= 1) return encryptedText;

        int[] pattern = new int[encryptedText.length()];
        int rail = 0, direction = 1;
        for (int i = 0; i < encryptedText.length(); i++) {
            pattern[i] = rail;
            rail += direction;
            if (rail == 0 || rail == rails - 1) direction *= -1;
        }

        int[] counts = new int[rails];
        for (int p : pattern) counts[p]++;

        List<StringBuilder> fence = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < rails; i++) {
            fence.add(new StringBuilder(encryptedText.substring(index, index + counts[i])));
            index += counts[i];
        }

        StringBuilder result = new StringBuilder();
        int[] nextCharIdx = new int[rails];
        for (int p : pattern) {
            result.append(fence.get(p).charAt(nextCharIdx[p]++));
        }

        return result.toString();
    }
}