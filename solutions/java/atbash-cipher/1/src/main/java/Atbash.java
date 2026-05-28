public class Atbash {

    private final String plain =  "abcdefghijklmnopqrstuvwxyz0123456789";
    private final String cipher = "zyxwvutsrqponmlkjihgfedcba0123456789";

    public String encode(String word) {
        word = word.replace(" ", "").toLowerCase();
        word =word.replace(".", "");
        word =word.replace(",", "");
        
        String encode = "";
        int control;
        for (int i = 0; i < word.length(); i++) {
            control = plain.indexOf(word.substring(i, i + 1));
            if (i % 5 == 0&& i>0) {
                encode += " ";
                encode += cipher.substring(control, control+1);
            }else{
             encode += cipher.substring(control, control+1);
            }
        }
        return encode;
    }
    public String decode(String word) {
        word = word.replace(" ", "").toLowerCase();
        word =word.replace(".", "");
        word =word.replace(",", "");
        String decode = "";
        int control;
        for (int i = 0; i < word.length(); i++) {
            control = cipher.indexOf(word.substring(i, i + 1));
            
             decode += plain.substring(control, control+1);
            
        }
        return decode;
    }

}
