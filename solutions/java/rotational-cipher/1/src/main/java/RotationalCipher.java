class RotationalCipher {
    private int rotacion;
    private String alfa="abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    
    RotationalCipher(int shiftKey) {
        rotacion = shiftKey;
    }

    String rotate(String data) {
        if(rotacion == 0 || rotacion == 26) {
            return data;
        }
        String[] palabra = data.split("");
        String[] rota = new String[palabra.length];
        String control = "";
        
        for(int i = 0; i < palabra.length; i++) {
            char letra = palabra[i].charAt(0);
            int index = alfa.indexOf(Character.toLowerCase(letra));
            
            if(index != -1) {
                char rotada = alfa.charAt(index + rotacion);
                if(Character.isUpperCase(letra)) {
                    control = Character.toUpperCase(rotada) + "";
                } else {
                    control = rotada + "";
                }
            } else {
                control = palabra[i];
            }
            rota[i] = control;
        }

        return String.join("", rota);
    }
}
