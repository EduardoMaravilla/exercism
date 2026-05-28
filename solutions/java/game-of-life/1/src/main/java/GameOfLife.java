class GameOfLife {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}, {1, 1}, {-1,-1},{-1,1},{1,-1}};

    public int[][] tick(int[][] matrix) {
        if(matrix != null && matrix.length == 0){
            return matrix;
        }
        int[][] tick = new int[matrix.length][matrix[0].length];
        int count = 0, rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isLive(matrix, i, j,rows,cols)) {
                    tick[i][j] = 1;
                } else {
                    tick[i][j] = 0;
                }
            }
        }
        return tick;
    }

    private boolean isLive(int[][] matrix, int i, int j, int rows, int cols) {
        int count = 0;
        for (int[] direction : DIRECTIONS) {
            int x = i + direction[0];
            int y = j + direction[1];
            if (isValidDirection(x, y, rows, cols) ) {
                if (matrix[x][y] == 1){
                    count++;
                }
            }
        }
        if (matrix[i][j] == 1 && count > 1 && count < 4){
            return true;
        }else{
            return count == 3;
        }
    }

    private boolean isValidDirection(int x, int y,int rows, int cols) {
        return x >= 0 && y >= 0 && y <= rows-1 && x <= cols-1;
    }
}
