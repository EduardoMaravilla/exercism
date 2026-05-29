#pragma once

namespace complex_numbers {
    class Complex {
        double real_;
        double imag_;

    public:
        Complex(double x, double y);

        [[nodiscard]] double real() const;

        [[nodiscard]] double imag() const;

        friend Complex operator+(const Complex &c1, const Complex &c2);

        friend Complex operator+(double x, const Complex &c);

        friend Complex operator+(const Complex &x, double y);

        friend Complex operator-(const Complex &c1, const Complex &c2);

        friend Complex operator-(double x, const Complex &c);

        friend Complex operator-(const Complex &x, double y);

        friend Complex operator*(const Complex &c1, const Complex &c2);

        friend Complex operator*(double x, const Complex &c);

        friend Complex operator*(const Complex &x, double y);

        friend Complex operator/(const Complex &c1, const Complex &c2);

        friend Complex operator/(double x, const Complex &c);

        friend Complex operator/(const Complex &c, double y);

        [[nodiscard]] double abs() const;

        [[nodiscard]] Complex conj() const;

        [[nodiscard]] Complex exp() const;
    };
} // namespace complex_numbers
