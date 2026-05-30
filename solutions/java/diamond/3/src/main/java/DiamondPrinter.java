import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DiamondPrinter {

    List<String> printToList(char letter) {

        int size = (letter - 'A' + 1) * 2 - 1;

        char[][] diamond = new char[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(diamond[i], ' ');
        }

        int row = 0;
        for (char current = 'A'; current <= letter; current++) {
            int externalSpaces = letter - current;
            diamond[row][externalSpaces] = current;
            if (current != 'A') {
                int right = size - 1 - externalSpaces;
                diamond[row][right] = current;
            }
            row++;
        }
        for (int i = row - 2; i >= 0; i--) {
            diamond[row++] = diamond[i].clone();
        }

        List<String> result = new ArrayList<>(size);

        for (char[] line : diamond) {
            result.add(new String(line));
        }

        return result;
    }
}