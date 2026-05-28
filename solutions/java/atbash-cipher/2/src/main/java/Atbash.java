public class Atbash {
    private final String plain = "abcdefghijklmnopqrstuvwxyz0123456789";
    private final String cipher = "zyxwvutsrqponmlkjihgfedcba0123456789";
    public String encode(String word) {
        word = word.replaceAll("[^a-zA-z0-9]", "").toLowerCase();
        StringBuilder encode = new StringBuilder();
        int control;
        for (int i = 0; i < word.length(); i++) {
            control = plain.indexOf(word.substring(i, i + 1));
            if (i % 5 == 0 && i > 0) {
                encode.append(" ");
            }
            encode.append(cipher.charAt(control));
        }
        return encode.toString();
    }
    public String decode(String word) {
        word = word.replaceAll("[^a-zA-z0-9]", "").toLowerCase();
        StringBuilder decode = new StringBuilder();
        int control;
        for (int i = 0; i < word.length(); i++) {
            control = cipher.indexOf(word.substring(i, i + 1));
            decode.append(plain.charAt(control));

        }
        return decode.toString();
    }
}