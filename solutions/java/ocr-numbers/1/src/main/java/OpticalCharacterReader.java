import java.util.List;

public class OpticalCharacterReader {

    private static final String[] DIGIT_PATTERNS = {
        " _ | ||_|", // 0
        "     |  |", // 1
        " _  _||_ ", // 2
        " _  _| _|", // 3
        "   |_|  |", // 4
        " _ |_  _|", // 5
        " _ |_ |_|", // 6
        " _   |  |", // 7
        " _ |_||_|", // 8
        " _ |_| _|"  // 9
    };

    public String parse(List<String> input) {
        if (input.size() % 4 != 0) {
            throw new IllegalArgumentException("Number of input rows must be a positive multiple of 4");
        }
        if (input.isEmpty() || input.get(0).length() % 3 != 0) {
            throw new IllegalArgumentException("Number of input columns must be a positive multiple of 3");
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.size(); i += 4) {
            String[] lines = {
                input.get(i),
                input.get(i + 1),
                input.get(i + 2)
            };
            result.append(parseSingle(lines));
            if (i + 4 < input.size()) {
                result.append(",");
            }
        }
        return result.toString();
    }

    private String parseSingle(String[] lines) {
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < lines[0].length(); i += 3) {
            String segment = lines[0].substring(i, i + 3) +
                             lines[1].substring(i, i + 3) +
                             lines[2].substring(i, i + 3);
            int index = getIndex(segment);
            number.append(index != -1 ? index : "?");
        }
        return number.toString();
    }

    private int getIndex(String segment) {
        for (int i = 0; i < DIGIT_PATTERNS.length; i++) {
            if (DIGIT_PATTERNS[i].equals(segment)) {
                return i;
            }
        }
        return -1;
    }
}
