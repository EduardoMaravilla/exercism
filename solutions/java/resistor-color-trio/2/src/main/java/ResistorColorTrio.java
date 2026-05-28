public class ResistorColorTrio {

    String label(String[] colors) {
        long color1 = valor(colors[0]);
        long color2 = valor(colors[1]);
        long color3 = valor(colors[2]);
        
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