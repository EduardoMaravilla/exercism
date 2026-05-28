import math


def measure(bucket_one, bucket_two, goal, start_bucket):

    if goal > max(bucket_one, bucket_two):
        raise ValueError("Goal is too big")

    if goal % math.gcd(bucket_one, bucket_two) != 0:
        raise ValueError("Goal is impossible")

    if start_bucket == "one":
        b1, b2 = bucket_one, 0
        b1_cap, b2_cap = bucket_one, bucket_two
        b1_name, b2_name = "one", "two"
    else:
        b1, b2 = bucket_two, 0
        b1_cap, b2_cap = bucket_two, bucket_one
        b1_name, b2_name = "two", "one"

    moves = 1


    if b2_cap == goal:
        b2 = b2_cap
        moves += 1

    while b1 != goal and b2 != goal:
        if b1 == 0:

            b1 = b1_cap
        elif b2 == b2_cap:

            b2 = 0
        else:

            amount = min(b1, b2_cap - b2)
            b1 -= amount
            b2 += amount

        moves += 1

    if b1 == goal:
        return moves, b1_name, b2
    else:
        return moves, b2_name, b1
