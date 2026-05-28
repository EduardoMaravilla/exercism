class LargestSeriesProductCalculator {
    private final int[] numbers;
    LargestSeriesProductCalculator(String inputNumber) {
        numbers = new int[inputNumber.length()];
        for (int i = 0; i < inputNumber.length(); i++) {
            char c = inputNumber.charAt(i);
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("String to search may only contain digits.");
            }
            numbers[i] = Character.getNumericValue(c);
        }
    }
    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if (numberOfDigits < 0) {
            throw new IllegalArgumentException("Series length must be non-negative.");
        }
        if (numberOfDigits > numbers.length) {
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        }
        long maxProduct = 0;
        for (int i = 0; i <= numbers.length - numberOfDigits; i++) {
            long product = 1;
            for (int j = i; j < i + numberOfDigits; j++) {
                product *= numbers[j];
            }
            if (product > maxProduct) {
                maxProduct = product;
            }
        }
        return maxProduct;
    }
}