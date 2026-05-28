class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        if(stringToVerify.isEmpty()){
            return false;
        }
        stringToVerify=stringToVerify.replaceAll("-","");
        char chdi=stringToVerify.charAt(stringToVerify.length()-1);
        String nueva=stringToVerify.substring(0,stringToVerify.length()-1);
        nueva=nueva.toLowerCase();
        nueva=nueva.replaceAll("[a-z]","");
        
        
        int valores=nueva.length();
        
        if (chdi != 'X' && !Character.isDigit(chdi)) {       
           return false;           
        } else if (valores == 9) {       
           int[] numeros = new int[10];
           int suma=0;
           int conteo=0;
           for (int i = 0; i < 9; i++) {
                numeros[i] = Integer.parseInt(String.valueOf(nueva.charAt(i)));
           }
           if (chdi== 'X') {
                numeros[9]=10;
           } else if (Character.isDigit(chdi)) {
                numeros[9]=Character.getNumericValue(chdi); 
           } else {
                return false;
           }
            
           for (int j = 9; j >= 0; j--) {
                suma=suma+(j+1)*numeros[conteo];
                conteo++;
           }
           if ((suma % 11) == 0) {
                return true;
           } else {
                return false;
           }   
  
        } else {
           return false;
        }           
    }
}
