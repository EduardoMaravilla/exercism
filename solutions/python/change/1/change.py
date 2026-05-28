def _best_change(coins, desired_total):
    if desired_total == 0:
        return []

    if desired_total in coins:
        return [desired_total]

    current_best = []
    current_best_size = float('inf')

    for coin in coins:
        if coin < desired_total and desired_total // coin < current_best_size:
            best = [coin]
            best.extend(_best_change(coins, desired_total - coin))

            if sum(best) == desired_total and len(best) < current_best_size:
                current_best = best.copy()
                current_best_size = len(current_best)

    return current_best


def find_fewest_coins(coins, target):
    if target < 0:
        raise ValueError("target can't be negative")

    coins = sorted(coins, reverse=True)

    result = _best_change(coins, target)

    if sum(result) != target:
        raise ValueError("can't make target with given coins")

    return sorted(result)