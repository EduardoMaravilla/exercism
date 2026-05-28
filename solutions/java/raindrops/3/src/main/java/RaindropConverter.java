class RaindropConverter {
    String convert(int number) {
        StringBuilder lluvia = new StringBuilder();
        if (number % 3 == 0) {
            lluvia.append("Pling");
        }
        if (number % 5 == 0) {
            lluvia.append("Plang");
        }
        if (number % 7 == 0) {
            lluvia.append("Plong");
        }
        return lluvia.length() > 0 ? lluvia.toString() : Integer.toString(number);
    }
}