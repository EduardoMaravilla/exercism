public class CryptoSquare {

    private final String message;

    public CryptoSquare(String mensaje) {
        this.message = mensaje.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    public String getCiphertext() {
        if (message.isEmpty()) return "";
        int length = message.length();
        int columns = (int) Math.ceil(Math.sqrt(length));
        int rows = (int) Math.ceil((double) length / columns);

        StringBuilder padded = new StringBuilder(message);
        while (padded.length() < rows * columns) {
            padded.append(" ");
        }
        StringBuilder result = new StringBuilder();
        for (int c = 0; c < columns; c++) {
            if (c > 0) result.append(" ");
            for (int r = 0; r < rows; r++) {
                int index = r * columns + c;
                result.append(padded.charAt(index));
            }
        }
        return result.toString();
    }
}