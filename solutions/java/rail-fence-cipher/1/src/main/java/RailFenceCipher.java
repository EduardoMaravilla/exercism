public class RailFenceCipher {
    private int rails;

    public RailFenceCipher(int rails) {
        this.rails = rails;
    }

    public String getEncryptedData(String text) {
        char[][] fence = new char[rails][text.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                fence[i][j] = '\n'; // Fill the fence with newline characters
            }
        }

        int rail = 0;
        boolean dirDown = false;

        for (int i = 0; i < text.length(); i++) {
            if (rail == 0 || rail == rails - 1) {
                dirDown = !dirDown; // Change direction at the top and bottom rails
            }

            fence[rail][i] = text.charAt(i);

            if (dirDown) {
                rail++;
            } else {
                rail--;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (fence[i][j] != '\n') {
                    result.append(fence[i][j]);
                }
            }
        }

        return result.toString();
    }

    public String getDecryptedData(String encryptedText) {
        char[][] fence = new char[rails][encryptedText.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < encryptedText.length(); j++) {
                fence[i][j] = '\n';
            }
        }

        int rail = 0;
        boolean dirDown = false;

        for (int i = 0; i < encryptedText.length(); i++) {
            if (rail == 0 || rail == rails - 1) {
                dirDown = !dirDown;
            }

            fence[rail][i] = '*'; // Place a marker at the positions where characters will be placed

            if (dirDown) {
                rail++;
            } else {
                rail--;
            }
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

            if (fence[rail][i] != '\n') {
                result.append(fence[rail][i]);
            }

            if (dirDown) {
                rail++;
            } else {
                rail--;
            }
        }

        return result.toString();
    }
}

