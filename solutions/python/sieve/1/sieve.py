def primes(limit):
    is_composite = []
    result = []
    for _ in range(limit + 1):
        is_composite.append(True)

    for i in range(2,limit+1):
        if is_composite[i]:
            result.append(i)
            for j in range(i * 2, limit + 1, i):
                is_composite[j] = False

    return result
