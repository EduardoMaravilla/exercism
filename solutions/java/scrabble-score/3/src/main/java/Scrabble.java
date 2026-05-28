class Scrabble {
    private final String word;
    Scrabble(String word) {
        this.word = word.toUpperCase();
    }
    int getScore() {       
       return word.chars().map(c -> scoreLetters((char) c)).sum();
    }

    private int scoreLetters(char letter) {
        return switch (letter) {
            case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' -> 1;
            case 'D', 'G' -> 2;
            case 'B', 'C', 'M', 'P' -> 3;
            case 'F', 'H', 'V', 'W', 'Y' -> 4;
            case 'K' -> 5;
            case 'J', 'X' -> 8;
            case 'Q', 'Z' -> 10;
            default -> 0;
        };
    }
}