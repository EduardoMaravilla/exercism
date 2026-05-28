import java.util.HashSet;
import java.util.Set;
public class PangramChecker {
    private final String abc = "abcdefghijklmnopqrstuvwxyz";

    public boolean isPangram(String input) {
        Set<Character> letrasVistas = new HashSet<>();
        String sentence = input.replaceAll("[^a-zA-Z]", "").toLowerCase();
        for (int i = 0; i < sentence.length(); i++) {
            char letra = sentence.charAt(i);
            letrasVistas.add(letra);
        }
        return letrasVistas.size() == 26;
    }
}