class Queen:
    def __init__(self, row, column):
        if row < 0:
            raise ValueError("row not positive")
        if row > 7:
            raise ValueError("row not on board")
        if column < 0:
            raise ValueError("column not positive")
        if column > 7:
            raise ValueError("column not on board")
        self._row = row
        self._column = column

    @property
    def row(self):
        return self._row

    @property
    def column(self):
        return self._column

    def can_attack(self, another_queen):
        if self._row == another_queen.row and self._column == another_queen.column:
            raise ValueError("Invalid queen position: both queens in the same square")

        return (abs(self._row - another_queen.row) == abs(self._column - another_queen.column) or
                self._row == another_queen.row or self._column == another_queen.column)
