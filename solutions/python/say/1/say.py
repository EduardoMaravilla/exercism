def say(number):
    if number < 0 or number > 999_999_999_999:
        raise ValueError("input out of range")
    basics = ["zero", "one", "two", "three", "four", "five", "six",
             "seven", "eight", "nine", "ten", "eleven", "twelve",
             "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
             "eighteen", "nineteen"]
    tens = ["twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"]
    thousands = ["thousand", "million", "billion"]

    if number < 20:
        return basics[number]
    elif number < 100:
        return f"{tens[number//10 - 2]}{"" if number % 10 == 0 else f"-{basics[number % 10]}" }"
    elif number < 1000:
        return f"{basics[number // 100]} hundred{"" if number % 100 == 0 else f" {say(number % 100)}"}"
    else:
        for i in range(len(thousands)-1, -1,-1):
            divider = 10 ** ((i+1)*3)
            if number >= divider:
                return f"{say(number // divider)} {thousands[i]}{"" if number % divider == 0 else f" {say(number % divider)}"}"
        return ""
