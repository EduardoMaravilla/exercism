from collections import Counter
from functools import cache

BOOK_PRICE = 800
DISCOUNTS = [0, 0.05, 0.10, 0.20, 0.25]


def total(basket):
    if not basket:
        return 0

    counts = tuple(sorted(Counter(basket).values(), reverse=True))
    print(counts)
    return calculate_min_cost(counts)


@cache
def calculate_min_cost(counts):
    if not any(counts):
        return 0

    counts = tuple(c for c in counts if c > 0)
    num_diff_books = len(counts)

    best_cost = float('inf')

    for size in range(1, num_diff_books + 1):
        new_counts = list(counts)
        for i in range(size):
            new_counts[i] -= 1

        new_state = tuple(sorted(new_counts, reverse=True))

        current_cost = get_group_price(size) + calculate_min_cost(new_state)
        best_cost = min(best_cost, current_cost)

    return int(best_cost)


def get_group_price(size):
    return int(BOOK_PRICE * size * (1 - DISCOUNTS[size - 1]))