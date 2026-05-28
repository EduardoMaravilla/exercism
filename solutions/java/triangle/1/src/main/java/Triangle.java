
class Triangle {
    private double lado1, lado2, lado3;

    Triangle(double side1, double side2, double side3) throws TriangleException {
        if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
            throw new TriangleException();
        }
        if (side1 + side2 <= side3 || side1 + side3 <= side2 || side2 + side3 <= side1) {
            throw new TriangleException();
        }
        lado1 = side1;
        lado2 = side2;
        lado3 = side3;
    }

    boolean isEquilateral() {
        if (lado1 == lado2 && lado2 == lado3) {
            return true;
        }
        return false;
    }

    boolean isIsosceles() {
          if (lado1 == lado2 || lado1 == lado3 || lado2 == lado3) {
        return true;
    } else {
        return false;
    }
    }

    boolean isScalene() {
        if (lado1 != lado2 && lado1 != lado3 && lado2 != lado3) {
            return true;
        }
        return false;
    }
}
