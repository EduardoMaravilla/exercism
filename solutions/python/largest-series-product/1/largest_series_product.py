def largest_product(series, size):
    if size < 0:
        raise ValueError("span must not be negative")
    if len(series) < size:
        raise ValueError("span must not exceed string length")
    if not series.isdigit():
        raise ValueError("digits input must only contain digits")

    max_product = 0
    for i in range(0,len(series)- size + 1,1):
        temp = 1
        for j in range(size):
            temp *= int(series[i+j])
        if temp > max_product:
            max_product = temp

    return max_product
