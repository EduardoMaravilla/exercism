def triplets_with_sum(number):
    triplets = []
    for a in range(1, number // 3):
        numerator = number**2 - 2 * number * a
        denominator = 2 * (number - a)
        
        if numerator % denominator == 0:
            b = numerator // denominator
            if b > a:
                c = number - a - b
                triplets.append([a, b, c])
                
    return triplets