import itertools

def _solve():
    houses = [1, 2, 3, 4, 5]
    orderings = list(itertools.permutations(houses))

    for (red, green, ivory, yellow, blue) in orderings:
        if green != ivory + 1:
            continue

        for (englishman, spaniard, ukrainian, norwegian, japanese) in orderings:
            if englishman != red:
                continue
            if norwegian != 1:
                continue
            if abs(norwegian - blue) != 1:
                continue

            for (dog, snails, fox, horse, zebra) in orderings:
                if spaniard != dog:
                    continue

                for (coffee, tea, milk, oj, water) in orderings:
                    if coffee != green:
                        continue
                    if ukrainian != tea:
                        continue
                    if milk != 3:
                        continue

                    for (dancing, painter, reading, football, chess) in orderings:
                        if snails != dancing:
                            continue
                        if yellow != painter:
                            continue
                        if abs(reading - fox) != 1:
                            continue
                        if abs(painter - horse) != 1:
                            continue
                        if football != oj:
                            continue
                        if japanese != chess:
                            continue

                        nationalities = {
                            "Englishman": englishman,
                            "Spaniard": spaniard,
                            "Ukrainian": ukrainian,
                            "Norwegian": norwegian,
                            "Japanese": japanese
                        }

                        water_drinker = next(
                            n for n, pos in nationalities.items() if pos == water
                        )
                        zebra_owner = next(
                            n for n, pos in nationalities.items() if pos == zebra
                        )

                        return water_drinker, zebra_owner
    return None


# Cacheamos el resultado para no recalcular
_WATER, _ZEBRA = _solve()


def drinks_water():
    return _WATER


def owns_zebra():
    return _ZEBRA

