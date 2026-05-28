import math


def prime(nth_number):
    if nth_number < 1:
        raise ValueError("there is no zeroth prime")

    count = 0
    number = 2

    while True:
        if is_prime(number):
            count += 1
            if count == nth_number:
                return number
        number += 1


def is_prime(num):
    if num < 2:
        return False

    sqrt = int(math.sqrt(num))

    for i in range(2, sqrt + 1):
        if num % i == 0:
            return False

    return True
