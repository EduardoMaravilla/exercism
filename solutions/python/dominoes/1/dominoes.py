def _connect_chain(root, second):
    if not second:
        return root[:]

    result = root[:]

    if second[0][0] == second[-1][1]:
        target = second[0][0]

        for i, domino in enumerate(result[:-1]):
            if domino[1] == target:
                result[i + 1:i + 1] = second
                break

    return result


def _parcial_chain(dominoes, return_chain):
    if not dominoes:
        return []

    chain = [dominoes[0]]
    remaining = dominoes[1:]

    current = chain[0]

    while remaining:
        for i, (a, b) in enumerate(remaining):
            if current[1] == a:
                chain.append((a, b))
                break
            elif current[1] == b:
                chain.append((b, a))
                break
        else:
            break

        remaining.pop(i)
        current = chain[-1]

    return chain if return_chain else remaining


def can_chain(dominoes=None):
    if not dominoes:
        return []

    if len(dominoes) == 1:
        return dominoes if dominoes[0][0] == dominoes[0][1] else None

    main_chain = _parcial_chain(dominoes, True)

    if len(main_chain) == len(dominoes):
        return main_chain

    remaining = _parcial_chain(dominoes, False)
    extra_chains = []

    while remaining:
        extra_chains.append(_parcial_chain(remaining, True))
        remaining = _parcial_chain(remaining, False)

    for sub_chain in extra_chains:
        main_chain = _connect_chain(main_chain, sub_chain)

    return main_chain if len(main_chain) == len(dominoes) else None
