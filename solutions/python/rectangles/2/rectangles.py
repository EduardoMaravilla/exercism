def rectangles(strings):
    if not strings or not strings[0]:
        return 0

    rows = len(strings)
    cols = len(strings[0])

    corners = [(r, c) for r in range(rows) for c in range(cols) if strings[r][c] == '+']
    count = 0

    for i, (r1, c1) in enumerate(corners):
        for j in range(i + 1, len(corners)):
            r2, c2 = corners[j]

            if r1 != r2:
                continue

            if not all(strings[r1][k] in '+-' for k in range(c1 + 1, c2)):
                continue

            for r3 in range(r1 + 1, rows):
                if strings[r3][c1] not in '+|' or strings[r3][c2] not in '+|':
                    break

                if strings[r3][c1] == '+' and strings[r3][c2] == '+':
                    if all(strings[r3][k] in '+-' for k in range(c1 + 1, c2)):
                        count += 1

    return count