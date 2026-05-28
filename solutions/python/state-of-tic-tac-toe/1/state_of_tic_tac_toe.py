def count_win(games: list, c: str) -> int:
    return sum(1 for game in games if game == c *3)


def gamestate(board=None):
    if board is None:
        board = []
    flat_board = "".join(board)
    count_x = flat_board.count("X")
    count_o = flat_board.count("O")

    games = list(board)

    games.append(board[0][0] + board[1][1] + board[2][2])
    games.append(board[2][0] + board[1][1] + board[0][2])

    for i in range(len(board)):
        games.append(board[0][i] + board[1][i] + board[2][i])

    win_x = count_win(games, "X")
    win_o = count_win(games, "O")

    if win_x >= 1 and win_o >= 1:
        raise ValueError("Impossible board: game should have ended after the game was won")

    if count_x == count_o + 2:
        raise ValueError("Wrong turn order: X went twice")

    if count_o > count_x:
        raise ValueError("Wrong turn order: O started")

    if (win_x >= 1 and win_o == 0) or (win_x == 0 and win_o >= 1):
        return "win"
    elif count_x + count_o == 9:
        return "draw"
    else:
        return "ongoing"

