def annotate(garden):
    if not garden:
        return garden

    row_length = len(garden[0])

    for row in garden:
        # validar longitud
        if len(row) != row_length:
            raise ValueError("The board is invalid with current input.")

        # validar caracteres
        for char in row:
            if char not in (" ", "*"):
                raise ValueError("The board is invalid with current input.")
    result = []
    for i in range(len(garden)):
        row = garden[i]
        temp = ""
        for j in range(len(row)):
            if row[j] == " ":
                mines = _count_flowers(i,j,garden)
                temp += str(mines) if mines > 0 else " "
            else:
                temp += row[j]
        result.append(temp)
    return result


def _count_flowers(row, col, garden):
    mines = 0
    rows = len(garden)
    directions = [[-1, 0], [1, 0], [0, -1], [0, 1], [-1, -1], [-1, 1], [1, -1], [1, 1]]
    for direction in directions:
        new_row = row + direction[0]
        new_col = col + direction[1]
        if 0 <= new_row < rows:
            near_row = garden[new_row]
            if 0 <= new_col < len(near_row) and near_row[new_col] == "*":
                mines += 1
    return mines

