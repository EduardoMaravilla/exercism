
RESISTOR_COLORS=["black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"]

def label(colors):

    [color1, color2, color3, *_] = colors
    value = (RESISTOR_COLORS.index(color1) * 10 + RESISTOR_COLORS.index(color2)) * (10 ** (RESISTOR_COLORS.index(color3)))

    prefix = ""

    if value >= 1000000000:
        value = value // 1000000000
        prefix = "giga"
    elif value >= 1000000:
        value = value // 1000000
        prefix = "mega"
    elif value >= 1000:
        value = value // 1000
        prefix = "kilo"

    return f"{value} {prefix}ohms"