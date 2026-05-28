import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StateOfTicTacToe {
    public GameState determineState(String[] board) {
        int winX = 0, winO = 0;
        long countX = 0, countO = 0, countSpace = 0;
        String xRight = "" + board[0].charAt(0) + board[1].charAt(1) + board[2].charAt(2);
        String xLeft = "" + board[2].charAt(0) + board[1].charAt(1) + board[0].charAt(2);
        String game = String.join("", board);
        countX = game.chars().filter(c -> c == 'X').count();
        countO = game.chars().filter(c -> c == 'O').count();
        countSpace = game.chars().filter(c -> c == ' ').count();
        List<String> games = new ArrayList<>(Arrays.stream(board).toList());
        games.add(xLeft);
        games.add(xRight);
        for (int i = 0; i < board.length; i++) {
            games.add("" + board[0].charAt(i) + board[1].charAt(i) + board[2].charAt(i));
        }
        winX = countWin(games, "X");
        winO = countWin(games, "O");
        if(winX >= 1 && winO >=1){
            throw new IllegalArgumentException("Impossible board: game should have ended after the game was won");
        }else if (countX == countO + 2) {
            throw new IllegalArgumentException("Wrong turn order: X went twice");
        } else if (countO > countX) {
            throw new IllegalArgumentException("Wrong turn order: O started");
        } else if (winX >= 1 && winO == 0 || winX == 0 && winO >= 1) {
            return GameState.WIN;
        } else if ((countX + countO) == 9) {
            return GameState.DRAW;
        } 
        return GameState.ONGOING;
    }

    private boolean isWin(String s, String c) {
        if (s == null || s.isBlank()) {
            return false;
        }
        return s.equals(c.repeat(3));
    }

    private int countWin(List<String> games, String c) {
        int count = 0;
        for (String val : games) {
            if (isWin(val, c)) {
                count++;
            }
        }
        return count;
    }
}