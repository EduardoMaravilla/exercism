public class Bob {

    public String hey(String phrase) {
        String answer1 = "Sure.";
        String answer2 = "Whoa, chill out!";
        String answer3 = "Calm down, I know what I'm doing!";
        String answer4 = "Fine. Be that way!";
        String answer5 = "Whatever.";
        boolean isAllUpperCase=false;
        // Eliminar todos los espacios en blanco, tabulaciones y saltos de línea.
        phrase = phrase.replaceAll("\\s+", "");

        // Verificar si la cadena es vacía
        if (phrase.isEmpty()) {
            return answer4;
        }

        // Verificar si la cadena termina en signo de interrogación
        boolean isQuestion = phrase.endsWith("?");

        // Verificar si todas las letras son mayúsculas
          phrase=phrase.replaceAll("[^a-zA-Z]", "");
        if(phrase.length()>0){
            isAllUpperCase = phrase.matches("^[A-Z]*$");
        }else{
            isAllUpperCase = false;
        }
        

        if (isAllUpperCase && isQuestion) {
            return answer3;
        } else if (isAllUpperCase) {
            return answer2;
        } else if (isQuestion) {
            return answer1;
        } else {
            return answer5;
        }
    }

}
