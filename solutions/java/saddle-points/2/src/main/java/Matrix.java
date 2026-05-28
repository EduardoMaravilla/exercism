import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Matrix {
    private final List<List<Integer>> values;

    Matrix(List<List<Integer>> values) {
        this.values = values;
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        Set<MatrixCoordinate> saddlePoints = new HashSet<>();
        if (this.values.isEmpty()) {
            return saddlePoints;
        }
        int rows = values.size();
        int cols = values.get(0).size();
        for (List<Integer> row : this.values) {
            if (row.size() != cols) {
                throw new IllegalArgumentException("Not all rows have the same number of columns.");
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int value = this.values.get(i).get(j);
                boolean isSaddlePoint = true;
                for (int k = 0; k < cols; k++) {
                    if (value < this.values.get(i).get(k)) {
                        isSaddlePoint = false;
                        break;
                    }
                }
                if (isSaddlePoint) {
                    for (int k = 0; k < rows; k++) {
                        if (value > this.values.get(k).get(j)) {
                            isSaddlePoint = false;
                            break;
                        }
                    }
                }
                if (isSaddlePoint) {
                    saddlePoints.add(new MatrixCoordinate(i + 1, j + 1));
                }
            }
        }
        return saddlePoints;
    }
}