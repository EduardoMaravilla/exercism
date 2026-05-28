import java.awt.Point;
import java.util.*;

class GoCounting {
    private final int rows;
    private final int cols;
    char[][] charBoard;
    private Player[][] board;

    GoCounting(String board) {
        this.charBoard = board.lines().map(String::toCharArray).toArray(char[][]::new);
        this.rows = this.charBoard.length;
        this.cols = this.charBoard[0].length;
        this.board = new Player[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.board[i][j] = charToPlayer(this.charBoard[i][j]);
            }
        }
    }

    Player getTerritoryOwner(int x, int y) {
        if (this.board[y][x] != Player.NONE){
            return Player.NONE;
        }
        return solve(x,y,null);
    }

    Set<Point> getTerritory(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
        Set<Point> territory = new HashSet<>();
        if (board[y][x] != Player.NONE) {
            return territory;
        }
        solve(x, y, territory);
        return territory;
    }

    Map<Player, Set<Point>> getTerritories() {
        Map<Player, Set<Point>> territories = new HashMap<Player, Set<Point>>();
        territories.put(Player.BLACK, new HashSet<>());
        territories.put(Player.NONE, new HashSet<>());
        territories.put(Player.WHITE, new HashSet<>());
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] != Player.NONE || visited[i][j]) continue;

                Set<Point> territory = new HashSet<>();
                Player owner = solve(j, i, territory);
                territories.get(owner).addAll(territory);

                for (Point point : territory) {
                    visited[point.y][point.x] = true;
                }
            }
        }
        return territories;
    }

    private Player charToPlayer(char character) {
        switch (character) {
            case 'B' -> {
                return Player.BLACK;
            }
            case 'W' -> {
                return Player.WHITE;
            }
        }
        return Player.NONE;
    }

    private Player solve(int x, int y, Set<Point> territory) {
        boolean[][] visited = new boolean[rows][cols];
        Set<Player> owners = new HashSet<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        while (!queue.isEmpty()) {
            Point point = queue.remove();
            visited[point.y][point.x] = true;
            if (territory != null) territory.add(point);
            for (Point neighbor : getNeighbors(point)) {
                if (visited[neighbor.y][neighbor.x]) continue;
                if (board[neighbor.y][neighbor.x] == Player.NONE) {
                    queue.add(neighbor);
                    continue;
                }
                owners.add(board[neighbor.y][neighbor.x]);
            }
        }
        if (owners.size() == 1) {
            return owners.iterator().next();
        }
        return Player.NONE;
    }

    private List<Point> getNeighbors(Point point) {
        int[][] neighbors = {
                { point.x, point.y + 1 }, { point.x, point.y - 1 },
                { point.x + 1, point.y }, { point.x - 1, point.y }
        };
        List<Point> neighborsList = new ArrayList<>();
        for (int i = neighbors.length; i > 0; i--) {
            int[] neighbor = neighbors[i - 1];
            if (neighbor[0] < 0 || neighbor[0] >= cols || neighbor[1] < 0 || neighbor[1] >= rows) {
                continue;
            }
            neighborsList.add(new Point(neighbor[0], neighbor[1]));
        }
        return neighborsList;
    }
}