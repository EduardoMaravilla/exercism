class RaindropConverter {
    String convert(int number) {
        StringBuilder rain = new StringBuilder();
        if (number % 3 == 0) {
            rain.append("Pling");
        }
        if (number % 5 == 0) {
            rain.append("Plang");
        }
        if (number % 7 == 0) {
            rain.append("Plong");
        }
        return rain.length() > 0 ? rain.toString() : Integer.toString(number);
    }
}