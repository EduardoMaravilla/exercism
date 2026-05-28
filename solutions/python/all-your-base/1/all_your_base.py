def rebase(input_base, digits, output_base):
    if input_base < 2:
        raise ValueError("input base must be >= 2")
    if output_base < 2:
        raise ValueError("output base must be >= 2")
    if any(d < 0 or d >= input_base for d in digits):
        raise ValueError("all digits must satisfy 0 <= d < input base")
    
    # Convert from input base to base 10
    number_in_base_10 = sum(d * (input_base ** idx) for idx, d in enumerate(reversed(digits)))
    
    # Convert from base 10 to output base
    if number_in_base_10 == 0:
        return [0]
    result = []
    while number_in_base_10 > 0:
        result.append(number_in_base_10 % output_base)
        number_in_base_10 //= output_base
    return result[::-1]

