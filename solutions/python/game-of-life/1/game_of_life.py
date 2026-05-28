DIRECTIONS = [
    (0,1),(1,0),(-1,0),(0,-1),
    (1,1),(-1,-1),(-1,1),(1,-1)
]

def tick(matrix):
    if not matrix:
        return []

    rows = len(matrix)
    cols = len(matrix[0])

    result = [[0]*cols for _ in range(rows)]

    for i in range(rows):
        for j in range(cols):

            neighbors = sum(
                matrix[i+dx][j+dy]
                for dx, dy in DIRECTIONS
                if 0 <= i+dx < rows and 0 <= j+dy < cols
            )

            if matrix[i][j] == 1:
                result[i][j] = 1 if 2 <= neighbors <= 3 else 0
            else:
                result[i][j] = 1 if neighbors == 3 else 0

    return result