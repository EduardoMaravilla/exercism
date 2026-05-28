enum Allergen {
    CATS(128),
    POLLEN(64),
    CHOCOLATE(32),
    TOMATOES(16),
    STRAWBERRIES(8),
    SHELLFISH(4),
    PEANUTS(2),
    EGGS(1);
    private final int score;
    Allergen(int score) {
        this.score = score;
    }
    int getScore() {
        return score;
    }
}