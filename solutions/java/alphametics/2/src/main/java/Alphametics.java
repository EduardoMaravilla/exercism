
import java.util.*;
public class Alphametics {
    private final String[] words;
    private final String lastWord;
    private final int rows;
    private final int columns;
    public Alphametics(String puzzle) {
        puzzle = puzzle.replace(" ", "");
        this.words = puzzle.split("\\+|==");
        this.lastWord = this.words[this.words.length - 1];
        this.rows = this.words.length;
        this.columns = lastWord.length();
    }

    public Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        Map<Character, Integer> solution = new HashMap<>();
        isValidSolution();
        Set<Character> uniqueCharacters = uniqueCharacter();
        List<Character> characterList = new ArrayList<>(uniqueCharacters);
        int numCharacters = uniqueCharacters.size();

        int n = 10;
        int[] elements = new int[n];
        for (int i = 0; i < n; i++) {
            elements[i] = i;
        }
        List<int[]> permutations = generatePermutations(elements, numCharacters);
        for (int[] permutation : permutations) {
            for (int i = 0; i < numCharacters; i++) {
                solution.put(characterList.get(i), permutation[i]);
            }
            if (isSolutionValid(solution)) {
                return solution;
            }
        }
        throw new UnsolvablePuzzleException();
    }

    private boolean isSolutionValid(Map<Character, Integer> solution) {
        long sum = 0;
        long answer = 0;
        for (int i = 0; i < rows - 1; i++) {
            long value = getNumberValue(words[i], solution);
            sum += value;
        }
        answer = getNumberValue(lastWord, solution);

        if (sum == answer) {
            return sum >= (Math.pow(10, lastWord.length() - 1) - 1);
        }else{
            return false;
        }
    }

    private long getNumberValue(String word, Map<Character, Integer> solution) {
        long value = 0;
        for (char c : word.toCharArray()) {
            value = value * 10 + solution.get(c);
        }
        return value;
    }

    private void isValidSolution() throws UnsolvablePuzzleException {
        for (String word : this.words) {
            if (word.length() > this.columns) {
                throw new UnsolvablePuzzleException();
            }
        }
        if (this.rows <= 2) {
            throw new UnsolvablePuzzleException();
        }
    }
    private Set<Character> uniqueCharacter() {
        Set<Character> unique = new HashSet<>();
        for (int i = 0; i < this.rows; i++) {
            for (char c : this.words[i].toCharArray()) {
                if (Character.isLetter(c)) {
                    unique.add(c);
                }
            }
        }
        return unique;
    }

    public List<int[]> generatePermutations(int[] elements, int r) {
        List<int[]> result = new ArrayList<>();
        int[] data = new int[r];
        boolean[] used = new boolean[elements.length];
        generatePermutationsHelper(elements, data, used, 0, r, result);
        return result;
    }

    private void generatePermutationsHelper(int[] elements, int[] data, boolean[] used, int depth, int r, List<int[]> result) {
        if (depth == r) {
            result.add(data.clone());
            return;
        }
        for (int i = 0; i < elements.length; i++) {
            if (!used[i]) {
                data[depth] = elements[i];
                used[i] = true;
                generatePermutationsHelper(elements, data, used, depth + 1, r, result);
                used[i] = false;
            }
        }
    }
}