public class RailFenceCipher {
    private final int rails;

    public RailFenceCipher(int rails) {
        this.rails = rails;
    }

    public String getEncryptedData(String text) {
        char[][] fence = new char[rails][text.length()];        
        int rail = 0;
        boolean dirDown = false;
        for (int i = 0; i < text.length(); i++) {
            if (rail == 0 || rail == rails - 1) {
                dirDown = !dirDown;
            }
            fence[rail][i] = text.charAt(i);
            rail = dirDown ? rail + 1 : rail -1;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (fence[i][j] != '\u0000') {
                    result.append(fence[i][j]);
                }
            }
        }
        return result.toString();
    }

    public String getDecryptedData(String encryptedText) {
        char[][] fence = new char[rails][encryptedText.length()];        
        int rail = 0;
        boolean dirDown = false;
        for (int i = 0; i < encryptedText.length(); i++) {
            if (rail == 0 || rail == rails - 1) {
                dirDown = !dirDown;
            }
            fence[rail][i] = '*';
            rail = dirDown ? rail + 1 : rail -1;
        }
        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < encryptedText.length(); j++) {
                if (fence[i][j] == '*' && index < encryptedText.length()) {
                    fence[i][j] = encryptedText.charAt(index++);
                }
            }
        }
        rail = 0;
        dirDown = false;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < encryptedText.length(); i++) {
            if (rail == 0 || rail == rails - 1) {
                dirDown = !dirDown;
            }
            if (fence[rail][i] != '\u0000') {
                result.append(fence[rail][i]);
            }
            rail = dirDown ? rail + 1 : rail -1;            
        }
        return result.toString();
    }
}