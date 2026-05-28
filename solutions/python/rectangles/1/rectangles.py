import re


def rectangles(strings):
    count_rectangles = 0
    for i in range(len(strings)):
        for j in range(len(strings[i])):
            if strings[i][j] == "+":
                for k in range(j + 1, len(strings[i])):
                    if re.match(r"^\+[+-]*\+$", strings[i][j:k + 1]):
                        for l in range(i + 1, len(strings)):
                            if re.match(r"^[+|].*[+|]$", strings[l][j:k + 1]):
                                if re.match(r"^\+[+-]*\+$",strings[l][j:k + 1 ]):
                                    count_rectangles += 1
                            else:
                                break

    return count_rectangles