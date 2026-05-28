from collections import deque

WHITE = 0
BLACK = 1
NONE = 2


class Board:
    """Count territories of each player in a Go game

    Args:
        board (list[str]): A two-dimensional Go board
    """

    def __init__(self, board):
        self.go_board = board
        self.height = len(board)
        self.width = len(board[0]) if self.height > 0 else 0
        self.stone_map = {'W': WHITE, 'B': BLACK}

    def territory(self, x, y):
        """Find the owner and the territories given a coordinate on
           the board

        Args:
            x (int): Column on the board
            y (int): Row on the board

        Returns:
            (str, set): A tuple, the first element being the owner
                        of that area.  One of "W", "B", "".  The
                        second being a set of coordinates, representing
                        the owner's territories.
        """
        if not (0 <= x < self.width and 0 <= y < self.height):
            raise ValueError("Invalid coordinate")

        if self.go_board[y][x] != ' ':
            return NONE, set()

        territory_points = set()
        queue = deque([(x, y)])
        territory_points.add((x, y))

        border_stones = set()

        while queue:
            cx, cy = queue.popleft()

            for dx, dy in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
                nx, ny = cx + dx, cy + dy

                if 0 <= nx < self.width and 0 <= ny < self.height:
                    cell = self.go_board[ny][nx]
                    if cell == ' ':
                        if (nx, ny) not in territory_points:
                            territory_points.add((nx, ny))
                            queue.append((nx, ny))
                    else:
                        border_stones.add(self.stone_map[cell])

        if len(border_stones) == 1:
            owner = list(border_stones)[0]
        else:
            owner = NONE

        return owner, territory_points

    def territories(self):
        """Find the owners and the territories of the whole board

        Args:
            none

        Returns:
            dict(str, set): A dictionary whose key being the owner
                        , i.e. "W", "B", "".  The value being a set
                        of coordinates owned by the owner.
        """
        res = {BLACK: set(), WHITE: set(), NONE: set()}
        visited = set()

        for y in range(self.height):
            for x in range(self.width):
                if self.go_board[y][x] == ' ' and (x, y) not in visited:
                    owner, points = self.territory(x, y)
                    res[owner].update(points)
                    visited.update(points)

        return res