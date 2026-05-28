public class PhoneNumber {
    private String number;
    private String cleanNumber;
    private String cleanletter;
    private int con;
    
    public PhoneNumber(String number) {
        this.number = number;
          
          cleanletter=number;
          cleanletter=cleanletter.replace(" ","");
          cleanletter=cleanletter.replace("-","");
          cleanletter=cleanletter.replace("(","");
          cleanletter=cleanletter.replace(")","");
          cleanletter=cleanletter.replace(".","");
          cleanletter=cleanletter.replace("+","");
         // Verificamos que el número no contenga caracteres no permitidos
        if (cleanletter.matches(".*[a-zA-Z]+.*")) {
            throw new IllegalArgumentException("letters not permitted");
        }
        // Verificamos que el número sólo tenga dígitos
        if (!cleanletter.matches("[0-9]+")) {
            throw new IllegalArgumentException("punctuations not permitted");
        }
        
        // Eliminamos todos los caracteres que no sean dígitos 
        cleanNumber = number.replaceAll("\\D", "");

        // Verificamos la cantidad de dígitos del número limpio
        con = cleanNumber.length();
        if (con < 10) {
            throw new IllegalArgumentException("incorrect number of digits");
        }
        // Si el número tiene más de 11 dígitos, es inválido
        if (con > 11) {
            throw new IllegalArgumentException("more than 11 digits");
        }
        
        // Si el número tiene 11 dígitos, el primer dígito debe ser 1
        if (con == 11 && cleanNumber.charAt(0) != '1') {
            throw new IllegalArgumentException("11 digits must start with 1");
        }
        // Verificamos que el código de área no empiece con 0 o 1
        if (con == 11 && (cleanNumber.charAt(1) == '0' ) ){
            throw new IllegalArgumentException("area code cannot start with zero");
        } else if (con == 11 && (cleanNumber.charAt(1) == '1')) {
            throw new IllegalArgumentException("area code cannot start with one");
        }  else if (con == 11 && cleanNumber.charAt(0) != '1') {
            throw new IllegalArgumentException("invalid country code");
        }else if (con == 10 && cleanNumber.charAt(0) == '0') {
            throw new IllegalArgumentException("area code cannot start with zero");
        }else if (con == 10 && cleanNumber.charAt(0) == '1') {
            throw new IllegalArgumentException("area code cannot start with one");
        }

        // Verificamos que el código de intercambio no empiece con 0 o 1
        if (cleanNumber.charAt(con - 7) == '0') {
            throw new IllegalArgumentException("exchange code cannot start with zero");
        } else if (cleanNumber.charAt(con - 7) == '1') {
            throw new IllegalArgumentException("exchange code cannot start with one");
        }
        
        
    }

    public String getNumber() {

        // Si pasó todas las verificaciones, devolvemos el número limpio
        if(con==10){
            return cleanNumber;
        }else{
          return cleanNumber.substring(1);
        }
        
        
    }
}
