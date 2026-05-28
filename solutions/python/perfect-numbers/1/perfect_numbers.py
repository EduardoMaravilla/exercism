def classify(perfect_number):
    """ A perfect number equals the sum of its positive divisors.

    :param perfect_number: int a positive integer
    :return: str the classification of the input integer
    """
    if perfect_number <= 0:
        raise ValueError("Classification is only possible for positive integers.")

    divisor_sum = sum(
        div for div in range(1, perfect_number // 2 + 1)
        if perfect_number % div == 0
    )

    if divisor_sum == perfect_number and perfect_number != 1:
        return "perfect"
    elif divisor_sum < perfect_number or perfect_number == 1:
        return "deficient"
    else:
        return "abundant"
