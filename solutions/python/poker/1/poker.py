from collections import Counter

RANKS = {
    "2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7,
    "8": 8, "9": 9, "10": 10, "J": 11, "Q": 12,
    "K": 13, "A": 14
}

def parse_card(card):
    return RANKS[card[:-1]], card[-1]

def best_hands(hands):
    scored = [(hand_score(h), h) for h in hands]
    best_score = max(s for s, _ in scored)
    return [h for s, h in scored if s == best_score]

def hand_score(hand):
    cards = hand.split()
    parsed = [parse_card(c) for c in cards]
    values = sorted([v for v, s in parsed], reverse=True)
    suits  = [s for v, s in parsed]

    count  = Counter(values)
    groups = {}
    for v, c in count.items():
        groups.setdefault(c, []).append(v)
    for k in groups:
        groups[k].sort(reverse=True)

    is_flush    = len(set(suits)) == 1
    is_straight = is_consecutive(values)

    if is_straight and is_flush:
        return (8, (high_straight(values),))
    if 4 in groups:
        kickers = groups.get(1, [])
        return (7, tuple(groups[4] + kickers))
    if 3 in groups and 2 in groups:
        return (6, tuple(groups[3] + groups[2]))
    if is_flush:
        return (5, tuple(values))
    if is_straight:
        return (4, (high_straight(values),))
    if 3 in groups:
        kickers = sorted(groups.get(1, []), reverse=True)
        return (3, tuple(groups[3] + kickers))
    if 2 in groups and len(groups[2]) == 2:
        pairs   = sorted(groups[2], reverse=True)
        kickers = groups.get(1, [])
        return (2, tuple(pairs + kickers))
    if 2 in groups:
        kickers = sorted(groups.get(1, []), reverse=True)
        return (1, tuple(groups[2] + kickers))
    return (0, tuple(values))

def is_consecutive(values):
    if values == [14, 5, 4, 3, 2]:
        return True
    return all(values[i] - 1 == values[i + 1] for i in range(4))

def high_straight(values):
    return 5 if values == [14, 5, 4, 3, 2] else values[0]