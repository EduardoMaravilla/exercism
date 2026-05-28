# Globals for the directions
EAST = 0
NORTH = 1
WEST = 2
SOUTH = 3


class Robot:
    def __init__(self, direction=NORTH, x_pos=0, y_pos=0):
        self._direction = direction
        self._x_pos = x_pos
        self._y_pos = y_pos

    @property
    def direction(self):
        return self._direction

    @property
    def coordinates(self):
        return self._x_pos, self._y_pos

    def move(self, instructions):
        for move in instructions:
            match move:
                case "A":
                    self._advance()
                case "L":
                    self._turn_left()
                case "R":
                    self._turn_right()
                case _:
                    raise ValueError(f"Instrucción inválida: {move}")

    def _turn_left(self):
        self._direction = (self._direction + 1) % 4

    def _turn_right(self):
        self._direction = (self._direction - 1) % 4

    def _advance(self):
        match self._direction:
            case 1:
                self._y_pos += 1
            case 0:
                self._x_pos += 1
            case 3:
                self._y_pos -= 1
            case 2:
                self._x_pos -= 1
