class LuhnValidator {
    boolean isValid(String candidate) {
        candidate = candidate.replaceAll(" ", "");
        int longitud = candidate.length();
        if (!candidate.matches("\\d+") || longitud <= 1) {
            return false;
        }
        int result=0;
        boolean isLuhn= false;
        for (int i = longitud - 1; i >=0 ; i--) {
            int val=Character.getNumericValue(candidate.charAt(i));
            if(isLuhn){
                val *=2;
                if (val > 9){
                    val -=9;
                }
            }
            result += val;
            isLuhn = !isLuhn;
        }
        return result % 10 == 0;
    }
}