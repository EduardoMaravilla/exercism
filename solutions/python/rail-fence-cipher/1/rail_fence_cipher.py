def get_rail_pattern(length, rails):
    pattern = []
    rail = 0
    direction = 1
    for _ in range(length):
        pattern.append(rail)
        rail += direction
        if rail == 0 or rail == rails - 1:
            direction *= -1
    return pattern


def encode(text="", rails=1):
    if rails <= 1:
        return text

    pattern = get_rail_pattern(len(text), rails)
    result = [[] for _ in range(rails)]

    for i, char in enumerate(text):
        result[pattern[i]].append(char)

    return "".join(char for rail in result for char in rail)


def decode(text="", rails=1):
    if rails <= 1:
        return text

    pattern = get_rail_pattern(len(text), rails)
    counts = [0] * rails
    for r in pattern:
        counts[r] += 1
    rails_content = []
    index = 0
    for count in counts:
        rails_content.append(list(text[index:index + count]))
        index += count

    result = []
    for r in pattern:
        result.append(rails_content[r].pop(0))

    return "".join(result)