import re
import math


def cipher_text(plain_text):
    text = re.sub(r'[^a-z0-9]', '', plain_text.lower())

    if not text:
        return ""

    length = len(text)

    c = math.ceil(math.sqrt(length))
    r = math.ceil(length / c)

    text += " " * (c * r - length)

    rows = [text[i:i + c] for i in range(0, len(text), c)]

    result = []
    for i in range(c):
        col = "".join(row[i] for row in rows)
        result.append(col)

    return " ".join(result)
