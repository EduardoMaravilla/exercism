import java.util.*;

public class Alphametics {
    private final String[] words;
    private final String lastWord;
    private final boolean[] canBeZero = new boolean[256];
    private final char[] uniqueChars;
    private final int[] charToDigit = new int[256];
    private final boolean[] usedDigits = new boolean[10];

    public Alphametics(String puzzle) {
        String p = puzzle.replace(" ", "");
        this.words = p.split("\\+|==");
        this.lastWord = this.words[this.words.length - 1];
        
        Set<Character> charSet = new HashSet<>();
        Arrays.fill(canBeZero, true);
        
        for (String word : words) {
            for (char c : word.toCharArray()) charSet.add(c);
            if (word.length() > 1) canBeZero[word.charAt(0)] = false;
        }
        
        uniqueChars = new char[charSet.size()];
        int i = 0;
        for (char c : charSet) uniqueChars[i++] = c;
    }

    public Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        if (uniqueChars.length > 10) throw new UnsolvablePuzzleException();
        
        if (backtrack(0)) {
            Map<Character, Integer> result = new HashMap<>();
            for (char c : uniqueChars) result.put(c, charToDigit[c]);
            return result;
        }
        throw new UnsolvablePuzzleException();
    }

    private boolean backtrack(int index) {
        if (index == uniqueChars.length) {
            return isValid();
        }

        char c = uniqueChars[index];
        for (int digit = 0; digit <= 9; digit++) {
            if (!usedDigits[digit]) {
                if (digit == 0 && !canBeZero[c]) continue;
                
                usedDigits[digit] = true;
                charToDigit[c] = digit;
                
                if (backtrack(index + 1)) return true;
                
                usedDigits[digit] = false; // Backtrack
            }
        }
        return false;
    }

    private boolean isValid() {
        long sum = 0;
        for (int i = 0; i < words.length - 1; i++) sum += getVal(words[i]);
        return sum == getVal(lastWord);
    }

    private long getVal(String word) {
        long val = 0;
        for (char c : word.toCharArray()) val = val * 10 + charToDigit[c];
        return val;
    }
}