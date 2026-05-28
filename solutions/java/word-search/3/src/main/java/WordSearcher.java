import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


class WordSearcher {
    Map<String, Optional<WordLocation>> search(final Set<String> words, final char[][] grid) {
        Map<String, Optional<WordLocation>> searchWord = new HashMap<>();
        List<String> Horizontal = new ArrayList<>();
        List<String> Vertical = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;

        for (char[] value : grid) {
            StringBuilder chain = new StringBuilder();
            for (int j = 0; j < cols; j++) {
                chain.append(value[j]);
            }
            Horizontal.add(chain.toString());
        }
        for (int i = 0; i < cols; i++) {
            StringBuilder chain = new StringBuilder();
            for (char[] chars : grid) {
                chain.append(chars[i]);
            }
            Vertical.add(chain.toString());
        }
        for (String word : words) {
            StringBuilder revWord = new StringBuilder();
            for (int i = (word.length() - 1); i >= 0; i--) {
                revWord.append(word.charAt(i));
            }
            boolean isWordFound = false;
            for (int i = 0; i < Horizontal.size(); i++) {
                if (Horizontal.get(i).contains(word)) {
                    isWordFound = true;
                    int pos1 = Horizontal.get(i).indexOf(word);
                    int pos2 = word.length();
                    searchWord.put(word, Optional.of(new WordLocation(new Pair(pos1 + 1, i + 1), new Pair((pos1 + pos2), i + 1))));

                } else if (Horizontal.get(i).contains(revWord.toString())) {
                    isWordFound = true;
                    int pos1 = Horizontal.get(i).indexOf(revWord.toString());
                    int pos2 = revWord.length();
                    searchWord.put(word, Optional.of(new WordLocation(new Pair((pos1 + pos2), i + 1), new Pair(pos1 + 1, i + 1))));
                }
            }
            if (!isWordFound) {
                for (int i = 0; i < Vertical.size(); i++) {
                    if (Vertical.get(i).contains(word)) {
                        isWordFound = true;
                        int pos1 = Vertical.get(i).indexOf(word);
                        int pos2 = word.length();
                        searchWord.put(word, Optional.of(new WordLocation(new Pair(i + 1, pos1 + 1), new Pair(i + 1, (pos1 + pos2)))));
                    } else if (Vertical.get(i).contains(revWord.toString())) {
                        isWordFound = true;
                        int pos1 = Vertical.get(i).indexOf(revWord.toString());
                        int pos2 = revWord.length();
                        searchWord.put(word, Optional.of(new WordLocation(new Pair(i + 1, (pos1 + pos2)), new Pair(i + 1, pos1 + 1))));
                    }
                }
            }
            if (!isWordFound) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (searchDiagonal(word, i, j, 1, 1, grid)) {
                            searchWord.put(word, Optional.of(new WordLocation(new Pair(j + 1, i + 1), new Pair(j + word.length(), i + word.length()))));
                            isWordFound = true;

                        }
                        if (searchDiagonal(word, i, j, -1, -1, grid)) {
                            searchWord.put(word, Optional.of(new WordLocation(new Pair(j + 1, i + 1), new Pair(j - word.length() + 2, i - word.length() + 2))));
                            isWordFound = true;

                        }
                        if (searchDiagonal(word, i, j, 1, -1, grid)) {
                            isWordFound = true;
                            searchWord.put(word, Optional.of(new WordLocation(new Pair(j + 1, i + 1), new Pair(j - word.length() + 2, i + word.length()))));

                        }
                        if (searchDiagonal(word, i, j, -1, 1, grid)) {
                            isWordFound = true;
                            searchWord.put(word, Optional.of(new WordLocation(new Pair(j + 1, i + 1), new Pair(j + word.length(), i - word.length() + 2))));
                        }
                    }
                    if (isWordFound) {
                        break;
                    }
                }
            }
            if (!isWordFound) {
                searchWord.put(word, Optional.empty());
            }

        }
        return searchWord;
    }

    private boolean searchDiagonal(String word, int row, int col, int rowInc, int colInc, char[][] grid) {
        int len = word.length();
        if (row + rowInc * (len - 1) >= grid.length || col + colInc * (len - 1) >= grid[0].length || row + rowInc * (len - 1) < 0 || col + colInc * (len - 1) < 0) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (grid[row + i * rowInc][col + i * colInc] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}