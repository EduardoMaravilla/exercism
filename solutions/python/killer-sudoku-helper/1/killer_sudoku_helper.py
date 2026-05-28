def combinations(target, size, exclude):
    result = []
    exclude_set = set(exclude)

    def backtrack(step_sum, remaining_size, next_number, current):
        if remaining_size == 0:
            if step_sum == target:
                result.append(current)
            return

        for i in range(next_number, 10):
            if i not in exclude_set:
                backtrack(
                    step_sum + i,
                    remaining_size - 1,
                    i + 1,
                    current + [i]
                )

    backtrack(0, size, 1, [])
    return result
