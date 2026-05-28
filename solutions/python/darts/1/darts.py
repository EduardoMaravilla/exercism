import math


def score(x, y):
    points = math.hypot(x,y)
    if points <= 1.0:
        result = 10
    elif points <= 5.0:
        result = 5
    elif points <= 10.0:
        result = 1
    else:
        result = 0
    return result