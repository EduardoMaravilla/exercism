public class ComplexNumber {
    private double real;
    private double imaginary;
    public ComplexNumber(double real,double imginary) {
        this.real=real;
        this.imaginary=imginary;
    }

    public double getReal() {
        return this.real;
    }

    public double getImag() {
        return this.imaginary;
    }
    
    public ComplexNumber times(ComplexNumber complexNumber) {
        double realPart = this.real*complexNumber.getReal()-this.imaginary*complexNumber.getImag();
        double imagPart = this.imaginary*complexNumber.getReal() + this.real*complexNumber.getImag();
        ComplexNumber complexNumber1 = new ComplexNumber(realPart, imagPart);
        return complexNumber1;
    }    
    
    public ComplexNumber add(ComplexNumber complexNumber) {
        double realPart = this.real + complexNumber.getReal();
        double imagPart = this.imaginary + complexNumber.getImag();
        ComplexNumber complexNumber1 = new ComplexNumber(realPart, imagPart);
        return complexNumber1;
    }
    
    public ComplexNumber minus(ComplexNumber complexNumber) {
        double realPart = this.real - complexNumber.getReal();
        double imagPart = this.imaginary - complexNumber.getImag();
        ComplexNumber complexNumber1 = new ComplexNumber(realPart, imagPart);
        return complexNumber1;
    }
    public ComplexNumber div(ComplexNumber complexNumber) {
        double a = this.real;
        double b = this.imaginary;
        double c = complexNumber.getReal();
        double d = complexNumber.getImag();
        double realPart = (a * c + b * d) / (Math.pow(c, 2) + Math.pow(d, 2));
        double imagPart= (b * c - a * d)/(Math.pow(c, 2) + Math.pow(d, 2));
        ComplexNumber complexNumber1 = new ComplexNumber(realPart, imagPart);
        return complexNumber1;
    }
    
    public double abs() {
        double absValue=Math.sqrt((Math.pow(Math.abs(this.real), 2)+Math.pow(Math.abs(this.imaginary), 2)));
        return absValue;
    }
    
    public ComplexNumber conjugate() {
        ComplexNumber complexNumber1 = new ComplexNumber(this.real, -(this.imaginary));
        return complexNumber1;
    }
    
    public ComplexNumber exponentialOf() {
        double expReal = Math.exp(this.real);
        double cosImaginary = Math.cos(this.imaginary);
        double sinImaginary = Math.sin(this.imaginary);

        double expRealCos = expReal * cosImaginary;
        double expRealSin = expReal * sinImaginary;

        return new ComplexNumber(expRealCos, expRealSin);
    }
}
