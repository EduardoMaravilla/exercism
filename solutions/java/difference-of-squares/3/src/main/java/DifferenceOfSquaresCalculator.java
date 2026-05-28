class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
        return (int) Math.pow((double) ((input + 1) * input) / 2, 2);
    }

    int computeSumOfSquaresTo(int input) {
        return input * (input + 1) * (input * 2 + 1) / 6;
    }

    int computeDifferenceOfSquares(int input) {
        return computeSquareOfSumTo(input) - computeSumOfSquaresTo(input);
    }
}