import java.util.ArrayList;
import java.util.List;

class WordProblemSolver {
    int solve(final String wordProblem1) {
        String wordProblem = wordProblem1;
        int respuesta = 0;
        if (!wordProblem.contains("What is")) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }
        if (wordProblem.contains("multiplied") || wordProblem.contains("divided")) {
            if (!wordProblem.contains("by")) {
                throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
            }
        }
        wordProblem = wordProblem.replace("What is", "");
        wordProblem = wordProblem.replace("?", "").trim();
        if (wordProblem.length() == 0) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }
        wordProblem = wordProblem.replace(" by", "");
        String[] operations = wordProblem.split(" ");
        for (String operation : operations) {
            System.out.println("operation = " + operation);
        }
        
        List<Integer> numeros = new ArrayList<>();
        List<String> oper = new ArrayList<>();
        if (operations.length == 1) {
            return Integer.parseInt(operations[0]);
        }
       
        for (int i = 0; i < operations.length; i++) {
            try {               
                int num=Integer.parseInt(operations[i]);               
                    numeros.add(num);  

            } catch (Exception e) {
                    if (i==0 || i%2 ==0) {
                      throw new IllegalArgumentException("I'm sorry, I don't understand the question!"); 
                    }
                    if (operations[i].equals("plus")) {
                        oper.add(operations[i]);
                    }
                    if (operations[i].equals("minus")) {
                        oper.add(operations[i]);
                    }
                    if (operations[i].equals("multiplied")) {
                        oper.add(operations[i]);
                    }
                    if (operations[i].equals("divided")) {
                        oper.add(operations[i]);
                    }
                    if (operations[i].equals("cubed")) {
                        oper.add(operations[i]);
                    }    
            }
        }
        for (String string : oper) {
            System.out.println("string = " + string);
        }
        for (Integer numero : numeros) {
            System.out.println("numero = " + numero);
        }
        
        if (!(numeros.size() == oper.size() + 1)) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }
        
        
        for (int i=0;i<oper.size();i++) {
            String string=oper.get(i);
            switch (string) {
                case "plus":
                    if(i==0){
                    respuesta = numeros.get(i)+numeros.get(i+1);
                    }else{
                    respuesta += numeros.get(i+1);
                    }                   
                    break;
                case "minus":
                    if(i==0){
                    respuesta = numeros.get(i)-numeros.get(i+1);
                    }else{
                    respuesta -= numeros.get(i+1);
                    }                   
                    break;
                case "multiplied":
                    if(i==0){
                    respuesta = numeros.get(i)*numeros.get(i+1);
                    }else{
                    respuesta *= numeros.get(i+1);
                    }                   
                    break;
                case "divided":
                    if(i==0){
                    respuesta = numeros.get(i)/numeros.get(i+1);
                    }else{
                    respuesta /= numeros.get(i+1);
                    }                   
                    break;    
                default:
                    throw new AssertionError();
            }
 
        }

        return respuesta;
    }
}
