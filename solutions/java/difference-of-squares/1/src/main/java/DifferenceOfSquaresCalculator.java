import java.lang.Math;

class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
        int num1=0;
        int resultado;
        for(int i=1;i<=input;i++){
            num1=num1+i;
        }
        resultado=num1*num1;
        return resultado;
    }

    int computeSumOfSquaresTo(int input) {
        int num2=0;
        int resultado2;
        for(int j=1;j<=input;j++){
            num2=num2+j*j;
        }
        resultado2=num2;
        return resultado2;
    }

    int computeDifferenceOfSquares(int input) {
        int resultado3;
        resultado3=computeSquareOfSumTo(input)-computeSumOfSquaresTo(input);
        return resultado3;
    }

}
