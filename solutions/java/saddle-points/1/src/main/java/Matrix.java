import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Matrix {

    private final List<List<Integer>> values;

    public Matrix(List<List<Integer>> values) {
        this.values = values;
    }

    public Set<MatrixCoordinate> getSaddlePoints() {
        Set<MatrixCoordinate> saddlePoints = new HashSet<>();

        if (values.isEmpty()) {
            // No se pueden encontrar puntos de silla en una matriz vacía
            return saddlePoints;
        }

        // Obtener el número de filas y columnas en la matriz
        int rows = values.size();
        int cols = values.get(0).size();

        // Verificar que cada fila tenga el mismo número de columnas
        for (List<Integer> row : values) {
            if (row.size() != cols) {
                throw new IllegalArgumentException("Not all rows have the same number of columns.");
            }
        }

        // Encontrar los valores mínimos y máximos en cada fila y columna
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int value = values.get(i).get(j);
                boolean isSaddlePoint = true;
                for (int k = 0; k < cols; k++) {
                    // Verificar si el valor es el máximo en su fila
                    if (value < values.get(i).get(k)) {
                        isSaddlePoint = false;
                        break;
                    }
                }
                if (isSaddlePoint) {
                    for (int k = 0; k < rows; k++) {
                        // Verificar si el valor es el mínimo en su columna
                        if (value > values.get(k).get(j)) {
                            isSaddlePoint = false;
                            break;
                        }
                    }
                }
                if (isSaddlePoint) {
                    saddlePoints.add(new MatrixCoordinate(i+1, j+1));
                }
            }
        }

        return saddlePoints;
    }

}
