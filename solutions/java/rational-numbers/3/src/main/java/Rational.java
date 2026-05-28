class Rational {

    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) {
        int mcd = 0;
        if (Math.abs(numerator) > 0 && Math.abs(denominator) > 0) {
            mcd = calcularMCD(numerator, denominator);
        } else {
            mcd = 1;
        }

        if(numerator==0){
            this.numerator = 0;
            this.denominator = 1;
        }else if (denominator < 0) {
            this.numerator = -(numerator / mcd);
            this.denominator = -(denominator / mcd);
        } else {
            this.numerator = numerator / mcd;
            this.denominator = denominator / mcd;
        }
    }

    int getNumerator() {
        return this.numerator;
    }

    int getDenominator() {
        return this.denominator;
    }

     public Rational add(Rational rational) {
        int a1 = this.numerator;
        int b1 = this.denominator;
        int a2 = rational.getNumerator();
        int b2 = rational.getDenominator();
        
        if (a1 * b2 == -a2 * b1) {
            return new Rational(0, 1);
        }
        
        int commonDenominator = b1 * b2;
        int newNumerator = a1 * b2 + a2 * b1;

        return new Rational(newNumerator, commonDenominator);
    }

    public Rational subtract(Rational rational) {
        int a1 = this.numerator;
        int b1 = this.denominator;
        int a2 = rational.getNumerator();
        int b2 = rational.getDenominator();
        
        if (a1 == a2 && b1 == b2) {
            return new Rational(0, 1);
        }

        int commonDenominator = b1 * b2;
        int newNumerator = a1 * b2 - a2 * b1;

        return new Rational(newNumerator, commonDenominator);
    }

    public Rational multiply(Rational rational) {
        int a1 = this.numerator;
        int b1 = this.denominator;
        int a2 = rational.getNumerator();
        int b2 = rational.getDenominator();
        
        if (a1 == 0 || a2 == 0) {
            return new Rational(0, 1);
        }

        return new Rational((a1 * a2), (b1 * b2));
    }

    public Rational divide(Rational rational) {
        int a1 = this.numerator;
        int b1 = this.denominator;
        int a2 = rational.getNumerator();
        int b2 = rational.getDenominator();
        return new Rational((a1 * b2), (a2 * b1));
    }

    public Rational abs() {
        int a1 =Math.abs(this.numerator);
        int b1 =Math.abs(this.denominator);
        return new Rational(a1, b1);
    }

    public Rational pow(int pow) {
    if (pow == 0) {
        return new Rational(1, 1); 
    } else if (pow > 0) {
        int a = (int) Math.pow(this.numerator, pow);
        int b = (int) Math.pow(this.denominator, pow);
        return new Rational(a, b);
    } else { 
        int a = (int) Math.pow(this.denominator, -pow);
        int b = (int) Math.pow(this.numerator, -pow);
        return new Rational(a, b);
    }
}


    public double exp(double n) {
                return Math.pow(Math.pow(n, 1.0/denominator), numerator);
    }

    private int calcularMCD(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", this.getNumerator(), this.getDenominator());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !this.getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }

        Rational other = (Rational) obj;
        return this.getNumerator() == other.getNumerator()
                && this.getDenominator() == other.getDenominator();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + this.getNumerator();
        result = prime * result + this.getDenominator();

        return result;
    }
}
