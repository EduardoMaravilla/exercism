DIGIT_PATTERNS = [
    " _ | ||_|",  # 0
    "     |  |",  # 1
    " _  _||_ ",  # 2
    " _  _| _|",  # 3
    "   |_|  |",  # 4
    " _ |_  _|",  # 5
    " _ |_ |_|",  # 6
    " _   |  |",  # 7
    " _ |_||_|",  # 8
    " _ |_| _|",  # 9
]


def convert(ocr):
    if len(ocr) % 4 != 0:
        raise ValueError("Number of input lines is not a multiple of four")

    for line in ocr:
        if len(line) % 3 != 0:
            raise ValueError("Number of input columns is not a multiple of three")

    result = []

    for i in range(0, len(ocr), 4):
        lines = [ocr[i], ocr[i + 1], ocr[i + 2]]
        result.append(parse_single(lines))

    return ",".join(result)


def parse_single(lines):
    number = ""

    for i in range(0, len(lines[0]), 3):
        segment = (
            lines[0][i:i + 3] +
            lines[1][i:i + 3] +
            lines[2][i:i + 3]
        )

        try:
            digit = DIGIT_PATTERNS.index(segment)
            number += str(digit)
        except ValueError:
            number += "?"

    return number
