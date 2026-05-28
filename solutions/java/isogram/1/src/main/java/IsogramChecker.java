class IsogramChecker {

    boolean isIsogram(String phrase) {
        phrase = phrase.replaceAll(" ", "");
        phrase = phrase.replaceAll("-", "");
        int control = 0;
        int longitud = phrase.length();

        for (int i = 1; i <= longitud; i++) {
            for (int j = i + 1; j <= longitud; j++) {
                if (Character.toLowerCase(phrase.charAt(i-1)) == Character.toLowerCase(phrase.charAt(j-1))) {
                    control++;
                    break;
                }
            }
            if (control > 0) {
                break;
            }
        }

        if (control > 0) {
            return false;
        } else {
            return true;
        }
    }
}
