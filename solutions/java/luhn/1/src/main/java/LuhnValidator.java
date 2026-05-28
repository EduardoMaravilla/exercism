class LuhnValidator {

    boolean isValid(String candidate) {
        candidate = candidate.replaceAll(" ", "");
        int longi = candidate.length();
        candidate = candidate.toLowerCase();
        candidate = candidate.replaceAll("[^\\d]", "");
        int num = candidate.length();

        if (longi <= 1) {
            return false;
        } else if (longi == num) {
            int[] nume = new int[longi];
            int suma = 0;
            int resul = suma;

            for (int i = 0; i < longi; i++) {
                char c = candidate.charAt(i);
                int nu = Character.getNumericValue(c);
                nume[i] = nu;
            }

            for (int j = longi - 2; j >= 0; j -= 2) {
                suma = 0;
                suma = suma + 2 * nume[j];
                if (suma >= 10) {
                    suma = suma - 9;
                    resul = resul + suma;
                } else {
                    resul = resul + suma;
                }
            }
            for (int k = longi - 1; k >= 0; k -= 2) {
                suma = 0;
                suma = nume[k];
                resul = resul + suma;
            }
            if ((resul % 10) == 0) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }
}
