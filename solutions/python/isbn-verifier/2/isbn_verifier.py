"""ISBN-10 validation module."""

import re


def is_valid(isbn: str) -> bool:
    """Return True if the provided ISBN-10 string is valid."""

    if isbn == "":
        return False

    isbn = isbn.replace("-", "")
    check_character = isbn[-1]
    digits = isbn[:-1]

    if (check_character != "X" and not check_character.isdigit()) or re.search(r"[a-zA-Z]", digits):
        return False

    if len(digits) != 9:
        return False

    numbers = [int(digit) for digit in digits]

    if check_character == "X":
        numbers.append(10)
    else:
        numbers.append(int(check_character))

    total = sum(number * (10 - index) for index, number in enumerate(numbers))

    return total % 11 == 0
