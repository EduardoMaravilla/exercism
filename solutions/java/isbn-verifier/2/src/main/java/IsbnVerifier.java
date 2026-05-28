class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        if(stringToVerify.isEmpty()){
            return false;
        }
        stringToVerify=stringToVerify.replaceAll("-","");
        char verifyChar=stringToVerify.charAt(stringToVerify.length()-1);
        String digits=stringToVerify.substring(0,stringToVerify.length()-1);        

        if (verifyChar != 'X' && !Character.isDigit(verifyChar) || digits.matches(".*[a-zA-Z].*")) {
            return false;
        } else if ( digits.length() == 9) {
            int[] numbers = new int[10];
            int sum=0;
            int count=0;
            for (int i = 0; i < 9; i++) {
                numbers[i] = Integer.parseInt(String.valueOf(digits.charAt(i)));
            }
            if (verifyChar== 'X') {
                numbers[9]=10;
            } else if (Character.isDigit(verifyChar)) {
                numbers[9]=Character.getNumericValue(verifyChar);
            } else {
                return false;
            }
            for (int j = 9; j >= 0; j--) {
                sum=sum+(j+1)*numbers[count];
                count++;
            }
            return (sum % 11) == 0;

        } else {
            return false;
        }
    }
}