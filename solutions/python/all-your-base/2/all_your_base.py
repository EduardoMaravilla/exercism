def rebase(input_base, digits, output_base):
    if input_base < 2:
        raise ValueError("input base must be >= 2")
    if output_base < 2:
        raise ValueError("output base must be >= 2")
    if any(d < 0 or d >= input_base for d in digits):
        raise ValueError("all digits must satisfy 0 <= d < input base")

    number = 0
    for d in digits:
        number = number * input_base + d

    if number == 0:
        return [0]

    result = []
    while number:
        result.append(number % output_base)
        number //= output_base

    return result[::-1]