from itertools import zip_longest


def transpose(text):
    if not text:
        return ""

    rows = text.split("\n")
    transposed = zip_longest(*rows, fillvalue=None)
    result = []
    for row in transposed:
        line = "".join(char if char is not None else " " for char in row)
        result.append(line)
    res = []
    for i, line in enumerate(result):
        max_len_remaining = max((len(r.rstrip(" ")) for r in result[i:]), default=0)
        res.append(line[:max_len_remaining])

    return "\n".join(res)