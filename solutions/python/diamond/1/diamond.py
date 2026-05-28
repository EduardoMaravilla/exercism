def rows(letter):
    abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    pos = abc.index(letter)
    width = pos * 2 + 1
    diamond = []

    for i in range(pos + 1):
        ch = abc[i]
        outer = pos - i
        inner = 2 * i - 1

        if i == 0:
            line = " " * outer + ch + " " * outer
        else:
            line = " " * outer + ch + " " * inner + ch + " " * outer

        diamond.append(line)

    for i in range(pos - 1, -1, -1):
        diamond.append(diamond[i])

    return diamond