def sum_of_multiples(limit, multiples):
    return sum({i for m in multiples if m > 0 for i in range(m, limit, m)})
