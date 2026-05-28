class Luhn:
    def __init__(self, card_num=""):
        temp = card_num.replace(" ", "")
        self.is_luhn = False

        if len(temp) <= 1 or not temp.isdigit():
            return

        _sum = 0
        alt = False

        for i in reversed(temp):
            digit = int(i)
            if alt:
                digit *= 2
                if digit > 9:
                    digit -= 9
            _sum += digit
            alt = not alt

        self.is_luhn = _sum % 10 == 0

    def valid(self):
        return self.is_luhn