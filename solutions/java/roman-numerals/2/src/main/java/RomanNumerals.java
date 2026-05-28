class RomanNumerals {
    private int num;

    RomanNumerals(int num) {
        this.num = num;
    }

    public String getRomanNumeral() {
        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder romano = new StringBuilder();
        for (int i = 0; i < arabicValues.length; i++) {
            while (num >= arabicValues[i]) {
                romano.append(romanSymbols[i]);
                num -= arabicValues[i];
            }
        }
        return romano.toString();
    }
}