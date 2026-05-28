# Mapeos constantes fuera de la función
COLOR_MAP = {
    "black": 0, "brown": 1, "red": 2, "orange": 3, "yellow": 4,
    "green": 5, "blue": 6, "violet": 7, "grey": 8, "white": 9
}

TOLERANCE_MAP = {
    "grey": 0.05, "violet": 0.1, "blue": 0.25, "green": 0.5,
    "brown": 1, "red": 2, "gold": 5, "silver": 10
}

def resistor_label(colors):
    num_bands = len(colors)

    if num_bands == 1:
        return "0 ohms"

    if num_bands not in (4, 5):
        raise ValueError("Número de bandas no soportado (solo 4 o 5).")

    sig_count = 2 if num_bands == 4 else 3

    digits = "".join(str(COLOR_MAP[c]) for c in colors[:sig_count])
    multiplier = 10 ** COLOR_MAP[colors[sig_count]]
    value = int(digits) * multiplier

    tolerance = TOLERANCE_MAP.get(colors[-1])

    units = ["ohms", "kiloohms", "megaohms", "gigaohms"]
    unit_idx = 0

    while value >= 1000 and unit_idx < len(units) - 1:
        value /= 1000
        unit_idx += 1

    formatted_val = f"{value:g}"
    label = f"{formatted_val} {units[unit_idx]}"

    return f"{label} ±{tolerance}%" if tolerance else label