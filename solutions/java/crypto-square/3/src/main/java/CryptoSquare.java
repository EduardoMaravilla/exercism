public class CryptoSquare {

    private final String mensaje;

    public CryptoSquare(String mensaje) {
        this.mensaje = mensaje.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    public String getCiphertext() {
        int c = 0;
        int r = 0;
        int longitud = this.mensaje.length();
        StringBuilder text = new StringBuilder(this.mensaje);
        String code = "";
        for (int i = 1; i < longitud; i++) {
            c = i + 1;
            r = i;
            if (i * i >= longitud) {
                c = i;
                break;
            } else if (c * r >= longitud) {
                break;
            }
        }
        text.append("*".repeat(Math.max(0, (c * r - longitud))));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (c > 0) {
                if (i % c == 0 && i > 0) {
                    sb.append(' ');
                }
            }
            sb.append(text.charAt(i));
        }
        text = new StringBuilder(sb.toString());
        code=transpose(text.toString());
        return code;
    }

    public String transpose(String toTranspose) {
        StringBuilder transpose= new StringBuilder();
        String[] chains = toTranspose.split(" ");
        int control = chains[0].length();
        for (String chain : chains) {
            if (chain.length() > control) {
                control = chain.length();
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
            for (String chain : chains) {
                transpose.append(chain.charAt(i));
            }
            transpose = new StringBuilder(transpose.toString().trim());
            if (i<control-1) {
                transpose.append(" ");
            }
        }
        return transpose.toString().trim().replace("*", " ");
    }
}