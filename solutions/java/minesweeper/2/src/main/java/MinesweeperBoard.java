import java.util.ArrayList;
import java.util.List;

public class MinesweeperBoard {
     List<String> boardRows;
    MinesweeperBoard(List<String> boardRows) {
        this.boardRows=boardRows;
    }

    List<String> withNumbers() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < this.boardRows.size(); i++) {
            String row= this.boardRows.get(i);
            StringBuilder sb=new StringBuilder();
            for (int j = 0; j < row.length(); j++) {
                if(row.charAt(j) == ' '){
                    int mines = countMines(i,j);
                    if (mines != 0){
                        sb.append(mines);
                    }else{
                        sb.append(' ');
                    }                    
                }else {
                    sb.append(row.charAt(j));
                }
            }
            result.add(sb.toString());
        }
        return result;
    }

    private int countMines(int row, int col){
        int mines = 0;
        int rows = boardRows.size();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1},{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] direction : directions){
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if( newRow >=0 && newRow < rows){
                String nearRow= boardRows.get(newRow);
                if( newCol >= 0 && newCol < nearRow.length() && nearRow.charAt(newCol)=='*'){
                    mines++;
                }
            }
        }
        return mines;
    }
}