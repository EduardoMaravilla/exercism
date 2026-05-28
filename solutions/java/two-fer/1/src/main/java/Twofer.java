public class Twofer {
    public String twofer(String name) {
        String frase1="One for";
        String frase2="one for me.";
        if (name==null){
            name="you";
        }
        return frase1 + " " + name + ", " + frase2;
    }
}
