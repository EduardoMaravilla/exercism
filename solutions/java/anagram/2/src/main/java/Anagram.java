import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Anagram {
    private final String word;

    public Anagram(String word){
        this.word=word;
    }

    public List<String> match(List<String> candidates) {
        List<String> anagrams = new ArrayList<>();
        for (String candidate : candidates) {
            if (isAnagram(word, candidate)) {
                anagrams.add(candidate);
            }
        }
        return anagrams;
    }

    private boolean isAnagram(String word, String candidate) {
        String word1=word.toLowerCase();
        String word2=candidate.toLowerCase();
        if (word.length() != candidate.length()) {
            return false;
        }else if(word1.equalsIgnoreCase(word2)){
            return false;
        }
        char[] wordChars = word.toLowerCase().toCharArray();
        char[] candidateChars = candidate.toLowerCase().toCharArray();
        Arrays.sort(wordChars);
        Arrays.sort(candidateChars);
        return Arrays.equals(wordChars, candidateChars);
    }
}