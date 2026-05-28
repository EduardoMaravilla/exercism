def saddle_points(matrix):
    if not matrix:
        return []
    
    if any(len(row) != len(matrix[0]) for row in matrix):
        raise ValueError("irregular matrix")

    row_maxima = [max(row) for row in matrix]
    col_minima = [min(col) for col in zip(*matrix)]

    return [
        {"row": r + 1, "column": c + 1}
        for r, row in enumerate(matrix)
        for c, val in enumerate(row)
        if val == row_maxima[r] == col_minima[c]
    ]