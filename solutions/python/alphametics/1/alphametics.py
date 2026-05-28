import re
from itertools import permutations


def solve(puzzle=""):
    puzzle = puzzle.replace('\n', ' ')
    parts = re.split(r'==', puzzle)
    left_side_str = parts[0]
    right_side_str = parts[1]
    left_words = re.findall(r'[A-Z]+', left_side_str)
    right_words = re.findall(r'[A-Z]+', right_side_str)
    all_words = left_words + right_words

    unique_chars = sorted(set("".join(all_words)))
    leading_chars = {w[0] for w in all_words}

    coefficients = dict.fromkeys(unique_chars, 0)

    for word in left_words:
        for i, char in enumerate(reversed(word)):
            coefficients[char] += 10 ** i

    for word in right_words:
        for i, char in enumerate(reversed(word)):
            coefficients[char] -= 10 ** i

    char_list = unique_chars
    coeff_list = [coefficients[c] for c in char_list]
    leading_indices = [char_list.index(c) for c in leading_chars]

    for p in permutations(range(10), len(char_list)):
        is_leading_zero = False
        for idx in leading_indices:
            if p[idx] == 0:
                is_leading_zero = True
                break
        if is_leading_zero:
            continue

        total = 0
        for i in range(len(p)):
            total += p[i] * coeff_list[i]

        if total == 0:
            return dict(zip(char_list, p))

    return None