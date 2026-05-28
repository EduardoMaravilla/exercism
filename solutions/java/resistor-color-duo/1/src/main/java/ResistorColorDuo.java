class ResistorColorDuo {
    int value(String[] colors) {
        int color1 = 0;
        int color2 = 0;
        int resultado;
        for (int i = 0; i < colors.length; i++) {
            if (i == 0) {
                color1 = valor(colors[i]);
            } else if (i == 1) {
                color2 = valor(colors[i]);
            }
        }
        resultado = color1 * 10 + color2;
        return resultado;
    }

    int valor(String color) {
        switch (color) {
            case "black":
                return 0;
            case "brown":
                return 1;
            case "red":
                return 2;
            case "orange":
                return 3;
            case "yellow":
                return 4;
            case "green":
                return 5;
            case "blue":
                return 6;
            case "violet":
                return 7;
            case "grey":
                return 8;
            case "white":
                return 9;
            default:
                return 0;
        }
    }
}
