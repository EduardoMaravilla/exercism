def largest(min_factor, max_factor):
    """Given a range of numbers, find the largest palindromes which
       are products of two numbers within that range.

    :param min_factor: int with a default value of 0
    :param max_factor: int
    :return: tuple of (palindrome, iterable).
             Iterable should contain both factors of the palindrome in an arbitrary order.
    """
    if min_factor > max_factor:
        raise ValueError("min must be <= max")

    for product in range(max_factor ** 2, min_factor ** 2 - 1, -1):
        if _is_palindrome(product):
            factors = _factor_pairs(product, min_factor, max_factor)
            if factors:
                return product, factors

    return None, []


def smallest(min_factor, max_factor):
    """Given a range of numbers, find the smallest palindromes which
    are products of two numbers within that range.

    :param min_factor: int with a default value of 0
    :param max_factor: int
    :return: tuple of (palindrome, iterable).
    Iterable should contain both factors of the palindrome in an arbitrary order.
    """
    if min_factor > max_factor:
        raise ValueError("min must be <= max")

    for product in range(min_factor ** 2, max_factor ** 2 + 1):
        if _is_palindrome(product):
            factors = _factor_pairs(product, min_factor, max_factor)
            if factors:
                return product, factors

    return None, []


def _is_palindrome(n):
    s = str(n)
    return s == s[::-1]


def _factor_pairs(n, min_factor, max_factor):
    pairs = []
    for i in range(min_factor, int(n**0.5) + 1):
        if n % i == 0:
            j = n // i
            if min_factor <= j <= max_factor:
                pairs.append([i, j])
    return pairs