from itertools import groupby
import re


def encode(string: str) -> str:
    result = []

    for char, group in groupby(string):
        count = len(list(group))
        result.append((str(count) if count > 1 else "") + char)

    return "".join(result)


def decode(string: str) -> str:
    result = []

    for count, char in re.findall(r"(\d*)(\D)", string):
        result.append(char * (int(count) if count else 1))

    return "".join(result)