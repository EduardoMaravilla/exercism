public class Bob {
    public String hey(String phrase) {
        String answer1 = "Sure.";
        String answer2 = "Whoa, chill out!";
        String answer3 = "Calm down, I know what I'm doing!";
        String answer4 = "Fine. Be that way!";
        String answer5 = "Whatever.";
        boolean isAllUpperCase=false;        
        phrase = phrase.replaceAll("\\s+", "");
        if (phrase.isEmpty()) {
            return answer4;
        }        
        boolean isQuestion = phrase.endsWith("?");       
        phrase=phrase.replaceAll("[^a-zA-Z]", "");
        if(phrase.length()>0){
            isAllUpperCase = phrase.matches("^[A-Z]*$");
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