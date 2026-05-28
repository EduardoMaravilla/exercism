import re

def is_valid(isbn):
    if isbn == '':
        return False

    isbn = isbn.replace("-", "")
    isbn_verify = isbn[-1]
    digits = isbn[:-1]

    if (isbn_verify != 'X' and not isbn_verify.isdigit()) or re.search(r"[a-zA-Z]", digits):
        return False
    elif len(digits) == 9:
        numbers = [int(n) for n in digits]

        if isbn_verify == 'X':
            numbers.append(10)
        elif isbn_verify.isdigit():
            numbers.append(int(isbn_verify))
        else:
            return False

        total = sum(n * (10 - i) for i, n in enumerate(numbers))
        return total % 11 == 0
    else:
        return False
