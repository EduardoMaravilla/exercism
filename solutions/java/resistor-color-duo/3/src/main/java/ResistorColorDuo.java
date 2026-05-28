class ResistorColorDuo {
    int value(String[] colors) {        
        return valor(colors[0]) * 10 + valor(colors[1]);
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