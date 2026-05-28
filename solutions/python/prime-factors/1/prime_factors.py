def factors(value):
    primes = []
    if value <=1:
        return primes

    num = 2
    while value > 1:
        while value % num == 0:
            primes.append(num)
            value //= num

        num += 1
        if num * num > value > 1:
            primes.append(value)
            break

    return  primes
