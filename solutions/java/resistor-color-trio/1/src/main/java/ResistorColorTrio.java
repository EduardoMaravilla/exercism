public class ResistorColorTrio {

    String label(String[] colors) {
        long color1 = 0;
        long color2 = 0;
        long color3 = 0;
        for (long i = 0L; i < colors.length; i++) {
            if (i == 0) {
                color1 = valor(colors[(int) i]);
            } else if (i == 1) {
                color2 = valor(colors[(int) i]);
            } else if (i == 2) {
                color3 = valor(colors[(int) i]);
            }
        }
        long val = (long) ((color1 * 10L + color2) * Math.pow(10, color3));
        String prefijo = "";
        if (val > 1000000000L) {
            val /= 1000000000L;
            prefijo = "giga";
        } else if (val > 1000000L) {
            val /= 1000000L;
            prefijo = "mega";
        } else if (val > 1000L) {
            val /= 1000L;
            prefijo = "kilo";
        }
        return val + " " + prefijo + "ohms";
    }
    long valor(String color) {
        return switch (color) {
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
