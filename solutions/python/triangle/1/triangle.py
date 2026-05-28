def equilateral(sides):
    a, b, c = sides
    return  a == b and b == c and _is_valid(a,b,c)


def isosceles(sides):
    a, b, c = sides
    return  (a == b or b == c or c == a) and _is_valid(a,b,c)


def scalene(sides):
    a, b, c = sides
    return a != b and b != c and c != a and _is_valid(a, b, c)

def _is_valid(a,b,c):
    return a > 0 and b > 0 and c > 0 and (a + b > c) and ( b + c > a ) and ( c + a > b)