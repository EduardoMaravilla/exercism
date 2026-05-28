public class SpiralMatrixBuilder {
    
    int[][] buildMatrixOfSize(int size) {
        int[][] matrix = new int[size][size];
        int counter = 1;
        int top = 0, bottom = size - 1, left = 0, right = size - 1;

        while (counter <= size * size) {            
            for (int i = left; i <= right; i++) {
                matrix[top][i] = counter++;
            }
            top++;
            
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = counter++;
            }
            right--;
            
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = counter++;
            }
            bottom--;
            
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = counter++;
            }
            left++;
        }
        return matrix;
    }
}