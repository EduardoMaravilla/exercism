def roman(number):
    roman_simbols = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]
    decimal_values = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
    result = []
    for i in range(len(decimal_values)):
        while number >= decimal_values[i]:
            result.append(roman_simbols[i])
            number -= decimal_values[i]
    return  "".join(result)
