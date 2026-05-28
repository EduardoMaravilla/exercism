
class ConnectGame:
    def __init__(self, board=""):
        self._board = [row.strip().split() for row in board.splitlines() if row.strip()]

        self._directions = [
            (0, -1), (0, 1),
            (-1, 0), (-1, 1),
            (1, -1), (1, 0)
        ]

    def get_winner(self):
        rows = len(self._board)
        cols = len(self._board[0])

        for i in range(rows):
            if self._board[i][0] == 'X':
                visited = [[False] * cols for _ in range(rows)]
                if self._has_path(i, 0, 'X', visited):
                    return 'X'

        for j in range(cols):
            if self._board[0][j] == 'O':
                visited = [[False] * cols for _ in range(rows)]
                if self._has_path(0, j, 'O', visited):
                    return 'O'

        return ''

    def _has_path(self, row, col, player, visited):
        if player == 'X' and col == len(self._board[row]) - 1:
            return True
        if player == 'O' and row == len(self._board) - 1:
            return True

        visited[row][col] = True

        for dr, dc in self._directions:
            nr, nc = row + dr, col + dc

            if (self._is_valid_move(nr, nc) and
                not visited[nr][nc] and
                self._board[nr][nc] == player):

                if self._has_path(nr, nc, player, visited):
                    return True

        return False

    def _is_valid_move(self, row, col):
        return (
            0 <= row < len(self._board) and
            0 <= col < len(self._board[row])
        )