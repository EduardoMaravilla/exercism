class ComplexNumber {
    private final double real;
    private final double imaginary;
    ComplexNumber(double real, double imaginary) {
        this.real=real;
        this.imaginary=imaginary;
    }

    double getReal() {
        return this.real;
    }

    double getImaginary() {
        return this.imaginary;
    }

    double abs() {
                return Math.sqrt((Math.pow(Math.abs(this.real), 2)+Math.pow(Math.abs(this.imaginary), 2)));
    }

    ComplexNumber add(ComplexNumber other) {
        double realPart = this.real + other.getReal();
        double imaginaryPart = this.imaginary + other.getImaginary();
        return new ComplexNumber(realPart, imaginaryPart);
    }

    ComplexNumber subtract(ComplexNumber other) {
        double realPart = this.real - other.getReal();
        double imaginaryPart = this.imaginary - other.getImaginary();
        return new ComplexNumber(realPart, imaginaryPart);
    }
 
     ComplexNumber multiply(ComplexNumber other) {
        double realPart = this.real * other.getReal() - this.imaginary * other.getImaginary();
        double imaginaryPart = this.real * other.getImaginary() + this.imaginary * other.getReal();
        return new ComplexNumber(realPart, imaginaryPart);
    }

    ComplexNumber multiply(double factor) {
        double realPart = this.real * factor;
        double imaginaryPart = this.imaginary * factor;
        return new ComplexNumber(realPart, imaginaryPart);
    }

    ComplexNumber divide(ComplexNumber other) {
        double a = this.real;
        double b = this.imaginary;
        double c = other.getReal();
        double d = other.getImaginary();
        double realPart = (a * c + b * d) / (Math.pow(c, 2) + Math.pow(d, 2));
        double imagPart= (b * c - a * d)/(Math.pow(c, 2) + Math.pow(d, 2));
        return new ComplexNumber(realPart, imagPart);
    }

    ComplexNumber divide(double divisor) {
        double realPart = this.real / divisor;
        double imaginaryPart = this.imaginary / divisor;
        return new ComplexNumber(realPart, imaginaryPart);
    }

    ComplexNumber conjugate() {
        return new ComplexNumber(this.real, -(this.imaginary));
    }

    ComplexNumber exponentialOf() {
        double expReal = Math.exp(this.real);
        double cosImaginary = Math.cos(this.imaginary);
        double sinImaginary = Math.sin(this.imaginary);
        double expRealCos = expReal * cosImaginary;
        double expRealSin = expReal * sinImaginary;
        return new ComplexNumber(expRealCos, expRealSin);
    }
}