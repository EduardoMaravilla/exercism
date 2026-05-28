public class PascalsTriangleGenerator {

    public int[][] generateTriangle(int numRows) {
        if (numRows <= 0) {
            return new int[][]{};
        }

        int[][] triangle = new int[numRows][];
        for (int i = 0; i < numRows; i++) {
            triangle[i] = new int[i + 1];
            triangle[i][0] = 1; // First element of each row is 1
            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
            }
            triangle[i][i] = 1; // Last element of each row is 1
        }
        return triangle;
    }
}