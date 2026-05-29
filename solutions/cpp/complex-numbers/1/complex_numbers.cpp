#include "complex_numbers.h"

#include <cmath>

namespace complex_numbers {
    Complex::Complex(const double x, const double y) : real_{x}, imag_{y} {
    };

    double Complex::real() const {
        return real_;
    }

    double Complex::imag() const {
        return imag_;
    }

    double Complex::abs() const {
        return std::sqrt(real_ * real_ + imag_ * imag_);
    }

    Complex Complex::conj() const {
        return {real_, -imag_};
    }

    Complex Complex::exp() const {
        const double magnitude = std::exp(real_);
        const double angle = imag_;
        return {magnitude * cos(angle), magnitude * sin(angle)};
    }

    Complex operator+(const Complex &c1, const Complex &c2) {
        return {c1.real() + c2.real(), c1.imag() + c2.imag()};
    }

    Complex operator+(const double x, const Complex &c) {
        return {x + c.real(), c.imag()};
    }

    Complex operator+(const Complex &x, double y) {
        return {x.real() + y, x.imag()};
    }

    Complex operator-(const Complex &c1, const Complex &c2) {
        return {c1.real() - c2.real(), c1.imag() - c2.imag()};
    }

    Complex operator-(const double x, const Complex &c) {
        return {x - c.real(), -c.imag()};
    }

    Complex operator-(const Complex &x, double y) {
        return {x.real() - y, x.imag()};
    }

    Complex operator*(const Complex &c1, const Complex &c2) {
        return {c1.real() * c2.real() - c1.imag() * c2.imag(), c1.real() * c2.imag() + c1.imag() * c2.real()};
    }

    Complex operator*(const double x, const Complex &c) {
        return {x * c.real(), x * c.imag()};
    }

    Complex operator*(const Complex &x, double y) {
        return {x.real() * y, x.imag() * y};
    }

    Complex operator/(const Complex &c1, const Complex &c2) {
        const double dem = c2.real() * c2.real() + c2.imag() * c2.imag();
        return {
            (c1.real() * c2.real() + c1.imag() * c2.imag()) / dem, (c1.imag() * c2.real() - c1.real() * c2.imag()) / dem
        };
    }

    Complex operator/(const double x, const Complex &c) {
        return Complex{x,0} / c;
    }

    Complex operator/(const Complex &c, double y) {
        return {c.real() / y, c.imag() / y};
    }
} // namespace complex_numbers
