class LargestSeriesProductCalculator {
    private int[] numeros;

    LargestSeriesProductCalculator(String inputNumber) {
        for (int j = 0; j < inputNumber.length(); j++) {
            char d = inputNumber.charAt(j);
            if (!Character.isDigit(d)) {
                throw new IllegalArgumentException("String to search may only contain digits.");
            }
        }

        int input = inputNumber.length();
        numeros = new int[input];
        for (int i = 0; i < input; i++) {
            char c = inputNumber.charAt(i);
            int num = Character.getNumericValue(c);
            numeros[i] = num;
        }
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if (numberOfDigits < 0) {
            throw new IllegalArgumentException("Series length must be non-negative.");
        }
        if (numberOfDigits > numeros.length) {
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        }

        long maxProduct = 0;
        for (int i = 0; i <= numeros.length - numberOfDigits; i++) {
            long product = 1;
            for (int j = i; j < i + numberOfDigits; j++) {
                product *= numeros[j];
            }
            if (product > maxProduct) {
                maxProduct = product;
            }
        }
        return maxProduct;
    }
}
