public class RunLengthEncoding {
    public String encode(String word) {
        if (word.isEmpty()) {
            return "";
        }
        StringBuilder encoded = new StringBuilder();
        char prevChar = word.charAt(0);
        int count = 1;
        for (int i = 1; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (currentChar == prevChar) {
                count++;
            } else {
                encoded.append(count > 1 ? count : "").append(prevChar);
                prevChar = currentChar;
                count = 1;
            }
        }
        encoded.append(count > 1 ? count : "").append(prevChar);
        return encoded.toString();
    }
    public String decode(String word) {
        StringBuilder decoded = new StringBuilder();
        StringBuilder countBuffer = new StringBuilder();

        for (char letter : word.toCharArray()) {
            if (Character.isDigit(letter)) {
                countBuffer.append(letter);
            } else {
                int count = (countBuffer.length() == 0) ? 1 : Integer.parseInt(countBuffer.toString());
                decoded.append(String.valueOf(letter).repeat(count));
                countBuffer.setLength(0);
            }
        }
        return decoded.toString();
    }
}