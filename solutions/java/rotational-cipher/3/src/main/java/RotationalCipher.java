import java.util.Arrays;

class RotationalCipher {
    private final int rotation;
    private static final String ALFA ="abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    RotationalCipher(int shiftKey) {
        rotation = shiftKey;
    }
    String rotate(String data) {
        if(rotation == 0 || rotation == 26) {
            return data;
        }
        return Arrays.stream(data.split("")).map(s -> {
            if (Character.isLetter(s.charAt(0))){
                return getString(s.charAt(0));
            }else {
                return s;
            }
        }).reduce(String::concat).orElseThrow();
    }

    private String getString(char c) {
        if (Character.isUpperCase(c)){
            return String.valueOf(ALFA.charAt(ALFA.indexOf(Character.toLowerCase(c)) + rotation)).toUpperCase();
        }else {
            return String.valueOf(ALFA.charAt(ALFA.indexOf(c) + rotation));
        }
    }
}