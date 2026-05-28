public class Connect {
    private final String[] board;
    private final int[][] directions = {{0, -2}, {0, 2}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public Connect(String[] board) {
        this.board = board;
    }

    public Winner computeWinner() {
        for (int i = 0; i < board.length; i++) {
            char play= board[i].charAt(i);
            boolean[][] visits= new boolean[board.length][board[board.length-1].length()];
            if ( play == 'X' && hasPath(i, i, 'X', visits)) {
                return Winner.PLAYER_X;
            }
        }
        for (int j = 0; j < board[0].length(); j++) {
            char play=board[0].charAt(j);
            boolean[][] visits= new boolean[board.length][board[board.length-1].length()];
            if ( play == 'O' && hasPath(0, j, 'O', visits)) {
                return Winner.PLAYER_O;
            }
        }
        return Winner.NONE;
    }

    private boolean hasPath(int row, int col, char player, boolean[][] visited) {
        if (col == board[row].length() - 1 && player == 'X') {
            return true;
        }
        if (row == board.length - 1 && player == 'O') {
            return true;
        }

        visited[row][col] = true;
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isValidMove(newRow, newCol) && !visited[newRow][newCol] && board[newRow].charAt(newCol) == player) {
                if (hasPath(newRow, newCol, player, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[board.length-1].length();
    }
}