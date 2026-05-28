import java.util.List;

public class PangramChecker {

    public boolean isPangram(String input){
        String sentence=input.toLowerCase();
        String abc="abcdefghijklmnopqrstuvwxyz";
        int num=sentence.length();
        int sum=0;
        String negativa="";
        for(int i=0;i<num;i++){
            String val=sentence.substring(i,i+1);
            if(abc.contains(val)){
                if(!negativa.contains(val)){
                    sum++;
                    negativa=negativa+val;
                }
            }
        }
        if(sum>=26){
          return true;  
        }else{
           return false; 
        }
    }
}
