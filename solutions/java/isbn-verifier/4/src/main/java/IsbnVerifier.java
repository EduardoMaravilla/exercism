class IsbnVerifier {

    boolean isValid(String input) {
        String isbn = input.replaceAll("-", "");
        if (!isbn.matches("\\d{9}[\\dX]")) return false;

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            char c = isbn.charAt(i);
            int value = (c == 'X') ? 10 : c - '0';
            sum += value * (10 - i);
        }
        return sum % 11 == 0;
    }
}