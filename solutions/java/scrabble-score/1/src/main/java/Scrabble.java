class Scrabble {
    private String word;
    private String palabra;
    
    Scrabble(String word) {
        this.word = word;
        palabra = word;
    }

    int getScore() {
        String letras = "aeioulnrstdgbcmpfhvwykjxqz";
        int resultado = 0;
        palabra = palabra.toLowerCase();
        
        for(int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            int puntuacion = 0;
            for(int j = 0; j < letras.length(); j++) {
                if(letras.charAt(j) == letra) {
                    if(j <=9) {
                        puntuacion = 1;
                    } else if(j <= 11) {
                        puntuacion = 2;
                    } else if(j <= 15) {
                        puntuacion = 3;
                    } else if(j <= 20) {
                        puntuacion = 4;
                    } else if(j <= 21) {
                        puntuacion = 5;
                    } else if(j <= 23) {
                        puntuacion = 8;
                    } else if(j <= 25) {
                        puntuacion = 10;
                    }
                    resultado += puntuacion;
                    break;
                }
            }
        }
        return resultado;
    }
}
