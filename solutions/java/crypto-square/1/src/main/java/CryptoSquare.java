public class CryptoSquare {

    private String mensaje;

    public CryptoSquare(String mensaje) {
        this.mensaje = mensaje.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    public String getCiphertext() {
        int c = 0;
        int r = 0;
        int longitud = this.mensaje.length();        
        String text = this.mensaje;
        String code = "";
        for (int i = 1; i < longitud; i++) {
            c = i + 1;
            r = i;
            if (i * i >= longitud) {
                c = i;
                r = i;
                break;
            } else if (c * r >= longitud) {
                break;
            }
        }
        for (int i = 0; i < (c*r-longitud); i++) {
            text +="*";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (c > 0) {
                if (i % c == 0 && i > 0) {
                    sb.append(' ');
                }
            }
            sb.append(text.charAt(i));
        }
        text = sb.toString();
        System.out.println("text = " + text);
        code=transpose(text);        
        return code;
    }
    
    public String transpose(String toTranspose) {        
        String traspose="";
        String[] chains = toTranspose.split(" ");     
        int control = chains[0].length();
        for (int i = 0; i < chains.length; i++) {
            if (chains[i].length() > control) {
                control = chains[i].length();
            }
        }
        for (int i = 0; i < chains.length; i++) {
            if (chains[i].length() < control) {
                int val=control-chains[i].length();
                for (int j = 0; j < val; j++) {
                  chains[i]=chains[i]+" ";  
                }
                
            }
        }
        for (int i = 0; i < control; i++) {
             for (int j = 0; j < chains.length; j++) {
                traspose=traspose+chains[j].substring(i, i+1);
            }
             traspose=traspose.trim();
            if (i<control-1) {
                traspose=traspose+" ";
            }
        }
        return traspose.trim().replace("*", " ");
    }

}

