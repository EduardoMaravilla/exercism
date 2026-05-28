from math import exp, cos, sin


class ComplexNumber:
    def __init__(self, real, imaginary):
        self.real = real
        self.imaginary = imaginary

    def __eq__(self, other):
        if isinstance(other, (int, float)):
            return self.real == other and self.imaginary == 0
        return self.real == other.real and self.imaginary == other.imaginary

    def __add__(self, other):
        if isinstance(other, (int, float)):
            return ComplexNumber(self.real + other, self.imaginary)
        return ComplexNumber(self.real + other.real, self.imaginary + other.imaginary)

    def __radd__(self, other):
        return self.__add__(other)

    def __mul__(self, other):
        if isinstance(other, (int, float)):
            return ComplexNumber(self.real * other, self.imaginary * other)
        return ComplexNumber(
            self.real * other.real - self.imaginary * other.imaginary,
            self.real * other.imaginary + self.imaginary * other.real
        )

    def __rmul__(self, other):
        return self.__mul__(other)

    def __sub__(self, other):
        if isinstance(other, (int, float)):
            return ComplexNumber(self.real - other, self.imaginary)
        return ComplexNumber(self.real - other.real, self.imaginary - other.imaginary)

    def __rsub__(self, other):
        return ComplexNumber(other - self.real, -self.imaginary)

    def __truediv__(self, other):
        if isinstance(other, (int, float)):
            return ComplexNumber(self.real / other, self.imaginary / other)

        denominator = other.real ** 2 + other.imaginary ** 2
        new_real = (self.real * other.real + self.imaginary * other.imaginary) / denominator
        new_imaginary = (self.imaginary * other.real - self.real * other.imaginary) / denominator
        return ComplexNumber(new_real, new_imaginary)

    def __rtruediv__(self, other):
        denominator = self.real ** 2 + self.imaginary ** 2
        return ComplexNumber(
            (other * self.real) / denominator,
            (-other * self.imaginary) / denominator
        )

    def __abs__(self):
        return (self.real ** 2 + self.imaginary ** 2) ** 0.5

    def conjugate(self):
        return ComplexNumber(self.real, -self.imaginary)

    def exp(self):
        magnitude = exp(self.real)
        angle = self.imaginary
        return ComplexNumber(magnitude * cos(angle), magnitude * sin(angle))
