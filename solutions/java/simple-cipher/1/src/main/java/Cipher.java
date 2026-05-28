import java.util.*;

public class Cipher {
    private String alphat="abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    private String randomKey;
    private String key;
    public Cipher() {
        Random random=new Random();
        int val=0;
        for (int i = 0; i < 100; i++) {
            val=random.nextInt(26);
            randomKey += alphat.substring(val,val+1);
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
        String code = "";
        if(this.key != null && this.key.length()<plainText.length()){
         int control=plainText.length()/this.key.length();
         String key1=this.key;
            for (int i = 0; i < control; i++) {
                this.key += key1;
            }
        }
        for (int i = 0; i < plainText.length(); i++) {
            val1 = alphat.indexOf(plainText.substring(i, i + 1));
            if (this.key !=null) {
                val2 = alphat.indexOf(key.substring(i, i + 1));
            }else{
               val2 = alphat.indexOf(randomKey.substring(i, i + 1));
            }  
            code += alphat.substring(val1 + val2, val1 + val2 + 1);
        }
        return code;
    }

    public String decode(String cipherText) {
        int val1;
        int val2;
        String code = "";
        for (int i = 0; i < cipherText.length(); i++) {
            val1 = alphat.lastIndexOf(cipherText.substring(i, i + 1));
            if (this.key !=null) {
                val2 = alphat.indexOf(key.substring(i, i + 1));
            }else{
               val2 = alphat.indexOf(randomKey.substring(i, i + 1));
            }  
            code += alphat.substring(val1 - val2, val1 - val2 + 1);
        }
        return code;
    }
}
