
RESISTOR_COLOR = ["black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"]

def value(colors):
    return int(RESISTOR_COLOR.index(colors[0]) * 10 + RESISTOR_COLOR.index(colors[1]))
