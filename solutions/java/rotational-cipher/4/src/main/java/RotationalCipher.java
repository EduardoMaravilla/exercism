class RotationalCipher {
    private final int rotation;

    RotationalCipher(int shiftKey) {
        this.rotation = shiftKey % 26;
    }

    String rotate(String input) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                char rotated = (char) ('A' + (ch - 'A' + rotation) % 26);
                result.append(rotated);
            } else if (Character.isLowerCase(ch)) {
                char rotated = (char) ('a' + (ch - 'a' + rotation) % 26);
                result.append(rotated);
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
}