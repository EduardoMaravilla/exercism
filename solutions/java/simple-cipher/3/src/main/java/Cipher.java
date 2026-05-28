import java.util.*;

public class Cipher {
    private final String alphat="abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    private String randomKey;
    private String key;
    public Cipher() {
        Random random=new Random();
        for (int i = 0; i < 100; i++) {
            randomKey += alphat.charAt(random.nextInt(26));
        }
    }

    public Cipher(String key) {
        this.key=key;
    }

    public String getKey() {
        if(this.key != null){
            return this.key;
        }else{
            return randomKey;
        }
    }

    public String encode(String plainText) {
        int val1;
        int val2;
        StringBuilder code = new StringBuilder();
        if(this.key != null && this.key.length()<plainText.length()){         
         this.key = this.key.repeat((int) (double) (plainText.length() / this.key.length())+1);
        }
        for (int i = 0; i < plainText.length(); i++) {
            val1 = alphat.indexOf(plainText.charAt(i));
            if (this.key !=null) {
                val2 = alphat.indexOf(key.charAt(i));
            }else{
               val2 = alphat.indexOf(randomKey.charAt(i));
            }  
            code.append(alphat.charAt(val1 + val2));
        }
        return code.toString();
    }

    public String decode(String cipherText) {
        int val1;
        int val2;
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            val1 = alphat.lastIndexOf(cipherText.charAt(i));
            if (this.key !=null) {
                val2 = alphat.indexOf(key.charAt(i));
            }else{
               val2 = alphat.indexOf(randomKey.charAt(i));
            }  
            code.append(alphat.charAt(val1 - val2));
        }
        return code.toString();
    }
}
