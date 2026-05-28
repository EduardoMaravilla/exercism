import java.util.Random;
public class MazeGenerator {
    private final char[] WALLS_SYMBOLS = {
                '┼', // 0000 No neighbors
                '│', // 0001 Up
                '│', // 0010 Down
                '│', // 0011 Up and Down
                '─', // 0100 Left
                '┘', // 0101 Left and Up
                '┐', // 0110 Left and Down
                '┤', // 0111 Left, Up, and Down
                '─', // 1000 Right
                '└', // 1001 Right and Up
                '┌', // 1010 Right and Down
                '├', // 1011 Right, Up, and Down
                '─', // 1100 Left and Right
                '┴', // 1101 Left, Right, and Up
                '┬', // 1110 Left, Right, and Down
                '┼', // 1111 All directions
        };
    private static final char PASSAGE = ' '; // Passage character
    private static final char ENTRANCE = '⇨'; // Entrance character
    private static final char EXIT = '⇨'; // Exit character (same as entrance for simplicity)

    private Random random;

    public char[][] generatePerfectMaze(int rows, int columns) { 
        return generateMaze(rows, columns, 0);
    }

    public char[][] generatePerfectMaze(int rows, int columns, int seed) {
        return generateMaze(rows, columns, seed);
    }

    private char[][] generateMaze(int rows, int columns, int seed) {
        if (rows < 5 || rows > 100 || columns < 5 || columns > 100) {
            throw new IllegalArgumentException("Invalid maze dimensions");
        }

        random = (seed == 0) ? new Random() : new Random(seed);
        int totalRows = rows * 2 + 1;
        int totalCols = columns * 2 + 1;

        // Create maze grid with walls
        char[][] maze = new char[totalRows][totalCols];
        for (int i = 0; i < totalRows-1; i++) {
            for (int j = 0; j < totalCols-1; j++) {
                maze[i][j] = getRandomWallSymbol();
            }
        }

        maze[0][0] = '┌';
        maze[0][totalCols-1] = '┐';
        maze[totalRows-1][0] = '└';
        maze[totalRows-1][totalCols-1] = '┘';
        for (int i = 1; i < totalRows-1; i++) {
            maze[i][0] = '│';
            maze[i][totalCols-1] = '│';
        }
        for (int i = 1; i < totalCols-1; i++) {
            maze[0][i] = '─';
            maze[totalRows-1][i] = '─';
        }
        // Choose random starting cell
        int startX = random.nextInt(totalRows);
        int exitX = random.nextInt(totalRows);
        int startY = 0;
        int exitY = totalCols - 1;
        // Place entrance and exit
        maze[startX][startY] = ENTRANCE;
        maze[startX][startY+1] = PASSAGE;
        maze[exitX][exitY] = EXIT;
        maze[exitX][exitY-1] = PASSAGE;

        // Generate maze recursively
        generateMazeRecursive(maze, startX, startY+1);
        return maze;
    }

    private void generateMazeRecursive(char[][] maze, int x, int y) {
        Direction[] directions = Direction.values();
        shuffleArray(directions);
        for (Direction direction : directions) {
            int newX = x + 2 * direction.dx();
            int newY = y + 2 * direction.dy();
            if (isValidCell(maze, newX, newY,direction)) {
                maze[newX][newY] = PASSAGE;
                maze[x + direction.dx()][y + direction.dy()] = PASSAGE;
                generateMazeRecursive(maze, newX, newY);
            }
        }
    }

    private boolean isValidCell(char[][] maze, int x, int y, Direction direction) {
        boolean secureMove = false;
        boolean validPosition = x > 0 && x < maze.length-1 && y > 0 && y < maze[0].length-1;
        if (validPosition){
            secureMove = switch (direction) {
                case NORTH -> maze[x][y] != PASSAGE && maze[x - 1][y] != PASSAGE;
                case SOUTH -> maze[x][y] != PASSAGE && maze[x + 1][y] != PASSAGE;
                case EAST -> maze[x][y] != PASSAGE && maze[x][y - 1] != PASSAGE;
                case WEST -> maze[x][y] != PASSAGE && maze[x][y + 1] != PASSAGE;
            };
        }
        return secureMove;
    }

    private char getRandomWallSymbol() {
        return WALLS_SYMBOLS[random.nextInt(WALLS_SYMBOLS.length)];
    }

    // Fisher-Yates shuffle algorithm
    private void shuffleArray(Direction[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int index = random.nextInt(array.length);
            Direction temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }

    private enum Direction {
        NORTH(-1, 0),
        EAST(0, 1),
        SOUTH(1, 0),
        WEST(0, -1);

        private final int dx;
        private final int dy;

        Direction(int dx, int dy) {
            this.dx = dx; 
            this.dy = dy;
        }

        public int dx() {
            return dx;
        }

        public int dy() {
            return dy;
        }
    }
}