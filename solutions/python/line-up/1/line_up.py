def line_up(name, number):

    number_string = str(number)
    if number_string[-1] == "1" and not number_string.endswith("11"):
        suffix = "st"
    elif number_string[-1] == "2" and not number_string.endswith("12"):
        suffix = "nd"
    elif number_string[-1] == "3" and not number_string.endswith("13"):
        suffix = "rd"
    else:
        suffix = "th"

    return f"{name}, you are the {number}{suffix} customer we serve today. Thank you!"
