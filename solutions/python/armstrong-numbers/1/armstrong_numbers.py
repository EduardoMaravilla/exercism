def is_armstrong_number(number):
    chain = str(number)
    length = len(chain)
    result = 0
    for num in str(number):
        result += pow(int(num),length)

    return result == number