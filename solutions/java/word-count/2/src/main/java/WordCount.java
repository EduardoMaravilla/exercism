import java.util.HashMap;
import java.util.Map;
public class WordCount {
    public Map<String, Integer> phrase(String input) {
        if (input.startsWith("'") && input.endsWith("'")) input = input.substring(1, input.length() - 1);
        Map<String, Integer> wordCount = new HashMap<>();
        input=input.replaceAll("[^\\p{Alnum}']+", " ").toLowerCase().trim();
        String[] words = input.split(" ");
        for (String word : words) {
            if (word.startsWith("'") && word.endsWith("'")) word = word.substring(1, word.length() - 1);
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        return wordCount;
    }
}