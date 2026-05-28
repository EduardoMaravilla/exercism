class Rational:
    def __init__(self, numer, denom):
        if abs(numer) > 0 and abs(denom) > 0:
            mcd = self._calulate_mcd(numer,denom)
        else:
            mcd = 1

        if numer == 0:
            self.numer = 0
            self.denom = 1
        elif denom > 0:
            self.numer = numer // mcd
            self.denom = denom // mcd
        else:
            self.numer = -numer // mcd
            self.denom = -denom // mcd

    def __eq__(self, other):
        if not isinstance(other, Rational):
            return False
        return self.numer == other.numer and self.denom == other.denom

    def __repr__(self):
        return f'{self.numer}/{self.denom}'

    def __add__(self, other):
        numerator = self.numer * other.denom + other.numer * self.denom
        denominator = self.denom * other.denom
        return Rational(numerator, denominator)

    def __sub__(self, other):
        numerator = self.numer * other.denom - other.numer * self.denom
        denominator = self.denom * other.denom
        return Rational(numerator, denominator)

    def __mul__(self, other):
        numerator = self.numer * other.numer
        denominator = self.denom * other.denom
        return Rational(numerator, denominator)

    def __truediv__(self, other):
        numerator = self.numer * other.denom
        denominator = self.denom * other.numer
        return Rational(numerator, denominator)

    def __abs__(self):
        return Rational(abs(self.numer), abs(self.denom))

    def __pow__(self, power):
        if power < 0:
            return Rational(self.denom ** abs(power), self.numer ** abs(power))
        else:
            return Rational(self.numer ** power, self.denom ** power)

    def __rpow__(self, base):
        return base ** (self.numer / self.denom)

    def _calulate_mcd(self,numerator,denominator):
        numerator = abs(numerator)
        denominator = abs(denominator)
        while denominator != 0:
            temp = denominator
            denominator = numerator % denominator
            numerator = temp
        return numerator