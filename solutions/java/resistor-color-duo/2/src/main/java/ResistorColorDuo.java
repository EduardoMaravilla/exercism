class ResistorColorDuo {
    int value(String[] colors) {
        int color1 = 0;
        int color2 = 0;
        for (int i = 0; i < colors.length; i++) {
            if (i == 0) {
                color1 = valor(colors[i]);
            } else if (i == 1) {
                color2 = valor(colors[i]);
            }
        }
        return color1 * 10 + color2;
    }

    int valor(String color) {
        return switch (color) {
            case "black" -> 0;
            case "brown" -> 1;
            case "red" -> 2;
            case "orange" -> 3;
            case "yellow" -> 4;
            case "green" -> 5;
            case "blue" -> 6;
            case "violet" -> 7;
            case "grey" -> 8;
            case "white" -> 9;
            default -> 0;
        };
    }
}