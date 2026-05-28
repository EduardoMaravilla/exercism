public class AffineCipher {

    private final String plain = "abcdefghijklmnopqrstuvwxyz";
    private final int M = 26;

    public String encode(String text, int coefficient1, int coefficient2) {
        if (calculateGCD(coefficient1, M) != 1) {
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        }        
        text = text.replaceAll("[ .,]", "").toLowerCase();    
        int letter = 0;
        StringBuilder encode = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isLetter(currentChar)) {
                letter = (coefficient1 * plain.indexOf(currentChar) + coefficient2) % M;
                if (i > 0 && i % 5 == 0) {
                    encode.append(" ");
                }
                encode.append(plain.charAt(letter));
            } else {
                if (i > 0 && i % 5 == 0) {
                    encode.append(" ");
                }
                encode.append(currentChar);
            }
        }
        return encode.toString();
    }

    public String decode(String text, int coefficient1, int coefficient2) {
    if (calculateGCD(coefficient1, M) != 1) {
        throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
    }
    int inverseCoefficient1 = findMultiplicativeInverse(coefficient1);
    text = text.replace(" ", "").toLowerCase();
    int letter = 0;
    StringBuilder decode = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
        char currentChar = text.charAt(i);
        if (Character.isLetter(currentChar)) {
            letter = (inverseCoefficient1 * (plain.indexOf(currentChar) - coefficient2 + M)) % M;

            if (letter < 0) {
                letter += M;
            }
            decode.append(plain.charAt(letter));
        } else {
            decode.append(currentChar);
        }
    }
    return decode.toString();
}
    private int calculateGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return calculateGCD(b, a % b);
    }    
    private int findMultiplicativeInverse(int a) {
        for (int i = 1; i < M; i++) {
            if ((a * i) % M == 1) {
                return i;
            }
        }
        throw new IllegalArgumentException("Multiplicative inverse not found for coefficient1: " + a);
    }
}