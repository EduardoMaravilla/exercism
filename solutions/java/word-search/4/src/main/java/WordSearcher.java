import java.util.*;

class WordSearcher {
    
    private record Line(String text, List<Pair> coords) {}

    Map<String, Optional<WordLocation>> search(final Set<String> words, final char[][] grid) {
        Map<String, Optional<WordLocation>> results = new HashMap<>();
        List<Line> allLines = getAllLines(grid);

        for (String word : words) {
            results.put(word, Optional.empty());
            
            for (Line line : allLines) {
                int index = line.text().indexOf(word);
                if (index != -1) {
                    Pair start = line.coords().get(index);
                    Pair end = line.coords().get(index + word.length() - 1);
                    results.put(word, Optional.of(new WordLocation(start, end)));
                    break; 
                }
            }
        }
        return results;
    }

    private List<Line> getAllLines(char[][] grid) {
        List<Line> lines = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            StringBuilder sb = new StringBuilder();
            List<Pair> coords = new ArrayList<>();
            for (int c = 0; c < cols; c++) {
                sb.append(grid[r][c]);
                coords.add(new Pair(c + 1, r + 1)); 
            }
            addLineAndReverse(lines, sb.toString(), coords);
        }

        for (int c = 0; c < cols; c++) {
            StringBuilder sb = new StringBuilder();
            List<Pair> coords = new ArrayList<>();
            for (int r = 0; r < rows; r++) {
                sb.append(grid[r][c]);
                coords.add(new Pair(c + 1, r + 1));
            }
            addLineAndReverse(lines, sb.toString(), coords);
        }

        Map<Integer, StringBuilder> mainTexts = new HashMap<>();
        Map<Integer, List<Pair>> mainCoords = new HashMap<>();
        Map<Integer, StringBuilder> antiTexts = new HashMap<>();
        Map<Integer, List<Pair>> antiCoords = new HashMap<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int mKey = r - c;
                int aKey = r + c;
                Pair p = new Pair(c + 1, r + 1);

                mainTexts.computeIfAbsent(mKey, k -> new StringBuilder()).append(grid[r][c]);
                mainCoords.computeIfAbsent(mKey, k -> new ArrayList<>()).add(p);
                
                antiTexts.computeIfAbsent(aKey, k -> new StringBuilder()).append(grid[r][c]);
                antiCoords.computeIfAbsent(aKey, k -> new ArrayList<>()).add(p);
            }
        }

        mainTexts.forEach((k, v) -> addLineAndReverse(lines, v.toString(), mainCoords.get(k)));
        antiTexts.forEach((k, v) -> addLineAndReverse(lines, v.toString(), antiCoords.get(k)));

        return lines;
    }

    private void addLineAndReverse(List<Line> lines, String text, List<Pair> coords) {
        lines.add(new Line(text, coords));
        
        String revText = new StringBuilder(text).reverse().toString();
        List<Pair> revCoords = new ArrayList<>(coords);
        Collections.reverse(revCoords);
        lines.add(new Line(revText, revCoords));
    }
}