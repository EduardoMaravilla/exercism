class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __eq__(self, other):
        if not isinstance(other, Point):
            return False
        return self.x == other.x and self.y == other.y

    def __repr__(self):
        return f"Point({self.x}, {self.y})"


class WordSearch:
    def __init__(self, puzzle):
        self.puzzle = puzzle
        self.rows = len(puzzle)
        self.cols = len(puzzle[0])

    def search(self, word):
        for text, coords in self._get_all_lines():
            if word in text:
                start = text.find(word)
                end = start + len(word) - 1
                return Point(coords[start][1], coords[start][0]), \
                    Point(coords[end][1], coords[end][0])
        return None

    def _get_all_lines(self):
        lines = []

        for r in range(self.rows):
            lines.append(("".join(self.puzzle[r]), [(r, c) for c in range(self.cols)]))
        for c in range(self.cols):
            lines.append(("".join(self.puzzle[r][c] for r in range(self.rows)), [(r, c) for r in range(self.rows)]))

        main, anti = {}, {}
        for r in range(self.rows):
            for c in range(self.cols):
                char, coord = self.puzzle[r][c], (r, c)
                main.setdefault(r - c, {"t": [], "c": []})
                anti.setdefault(r + c, {"t": [], "c": []})

                for d, k in [(main, r - c), (anti, r + c)]:
                    d[k]["t"].append(char)
                    d[k]["c"].append(coord)

        for d in list(main.values()) + list(anti.values()):
            text = "".join(d["t"])
            lines.append((text, d["c"]))

        return [(t, c) for t, c in lines] + [(t[::-1], c[::-1]) for t, c in lines]