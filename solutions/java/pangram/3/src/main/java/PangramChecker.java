import java.util.HashSet;
import java.util.Set;

public class PangramChecker {
    private final String abc = "abcdefghijklmnopqrstuvwxyz";

    public boolean isPangram(String input) {
        String sentence = input.toLowerCase();
        Set<Character> letrasVistas = new HashSet<>();

        for (int i = 0; i < input.length(); i++) {
            char letra = sentence.charAt(i);
            if (abc.indexOf(letra) != -1) {
                letrasVistas.add(letra);
            }
        }

        return letrasVistas.size() == 26;
    }
}
