# Score categories.
# Change the values as you see fit.
from collections import Counter

YACHT = 0
ONES = 1
TWOS = 2
THREES = 3
FOURS = 4
FIVES = 5
SIXES = 6
FULL_HOUSE = 7
FOUR_OF_A_KIND = 8
LITTLE_STRAIGHT = 9
BIG_STRAIGHT = 10
CHOICE = 11

def sum_of(value, dices):
    return dices.count(value) * value

def is_straight(dices):
    dices_sorted = sorted(dices)
    return all(
        i == 0 or dices_sorted[i] == dices_sorted[i - 1] + 1
        for i in range(len(dices_sorted))
    )

def score(dice, category):
    dices_sorted = sorted(dice)
    count = Counter(dices_sorted)
    keys = list(count.keys())
    result = 0
    match category:
        case 0:
            if all( d == dices_sorted[0] for d in dices_sorted):
                result = 50
        case 1:
            result = sum_of(1, dices_sorted)
        case 2:
            result = sum_of(2, dices_sorted)
        case 3:
            result = sum_of(3, dices_sorted)
        case 4:
            result = sum_of(4, dices_sorted)
        case 5:
            result = sum_of(5, dices_sorted)
        case 6:
            result = sum_of(6, dices_sorted)
        case 7:
            if (len(keys) == 2 and
                    (count[keys[0]] == 2 and count[keys[1]] == 3 or
                     count[keys[0]] == 3 and count[keys[1]] == 2)):
                result = sum(dices_sorted)
        case 8:
            for key in keys:
                if count[key] >= 4:
                    result = key * 4
        case 9:
            if is_straight(dices_sorted) and dices_sorted[0] == 1:
                result = 30
        case 10:
            if is_straight(dices_sorted) and dices_sorted[0] == 2:
                result = 30
        case 11:
            result = sum(dices_sorted)
    return result
