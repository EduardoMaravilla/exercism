import java.awt.Point;
import java.util.*;

public class GoCounting {
    private final char[][] board;
    private final int width, height;

    public GoCounting(String boardStr) {
        String[] lines = boardStr.split("\n");
        this.height = lines.length;
        this.width = lines[0].length();
        this.board = new char[height][width];
        for (int i = 0; i < height; i++) {
            board[i] = lines[i].toCharArray();
        }
    }

    public Player getTerritoryOwner(int x, int y) {
        return getFullTerritory(x, y).owner;
    }

    public Set<Point> getTerritory(int x, int y) {
        return getFullTerritory(x, y).points;
    }

    public Map<Player, Set<Point>> getTerritories() {
        Map<Player, Set<Point>> allTerritories = new HashMap<>();
        for (Player p : Player.values()) {
            allTerritories.put(p, new HashSet<>());
        }

        boolean[][] visited = new boolean[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board[y][x] == ' ' && !visited[y][x]) {
                    TerritoryResult res = getFullTerritory(x, y);
                    allTerritories.get(res.owner).addAll(res.points);
                    for (Point p : res.points) {
                        visited[p.y][p.x] = true;
                    }
                }
            }
        }
        return allTerritories;
    }
    
    private record TerritoryResult(Set<Point> points, Player owner) {}

    private TerritoryResult getFullTerritory(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Invalid coordinate");
        }

        Set<Point> points = new HashSet<>();
        if (board[y][x] != ' ') {
            return new TerritoryResult(points, Player.NONE);
        }

        Queue<Point> queue = new LinkedList<>();
        Set<Player> borderStones = new HashSet<>();
        
        Point start = new Point(x, y);
        queue.add(start);
        points.add(start);

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int[] d : directions) {
                int nx = current.x + d[0];
                int ny = current.y + d[1];

                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    if (board[ny][nx] == ' ') {
                        Point next = new Point(nx, ny);
                        if (points.add(next)) {
                            queue.add(next);
                        }
                    } else {
                        borderStones.add(board[ny][nx] == 'B' ? Player.BLACK : Player.WHITE);
                    }
                }
            }
        }

        Player owner = (borderStones.size() == 1) ? borderStones.iterator().next() : Player.NONE;
        
        return new TerritoryResult(points, owner);
    }
}