class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
        int num1 = 0;
        for (int i = 1; i <= input; i++) {
            num1 = num1 + i;
        }
        return num1 * num1;
    }

    int computeSumOfSquaresTo(int input) {
        int num = 0;
        for (int j = 1; j <= input; j++) {
            num = num + j * j;
        }        
        return num;
    }    
    int computeDifferenceOfSquares(int input) {
        return computeSquareOfSumTo(input) - computeSumOfSquaresTo(input);
    }
}