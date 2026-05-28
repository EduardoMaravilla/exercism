class IsbnVerifier {

    boolean isValid(String input) {
        if (input == null || input.isEmpty()) return false;

        String clean = input.replaceAll("-", "");
        if (clean.length() != 10 || !clean.substring(0, 9).matches("\\d{9}")) return false;

        char lastChar = clean.charAt(9);
        if (!Character.isDigit(lastChar) && lastChar != 'X') return false;

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (10 - i) * Character.getNumericValue(clean.charAt(i));
        }

        sum += (lastChar == 'X') ? 10 : Character.getNumericValue(lastChar);

        return sum % 11 == 0;
    }
}