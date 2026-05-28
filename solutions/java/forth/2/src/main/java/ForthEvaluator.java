import java.util.*;

public class ForthEvaluator {

    private Stack<Integer> stack;
    private Map<String, List<String>> wordDefinitions;

    public ForthEvaluator() {
        stack = new Stack<>();
        wordDefinitions = new HashMap<>();
    }

    public List<Integer> evaluateProgram(List<String> input) {
           
               for (int i = 0; i <input.size(); i++) {
                   String definition=input.get(i);
                   definition=definition.trim();
                   if (definition.startsWith(":") && definition.endsWith(";")) {
                       definition=definition.replace(":", "").replace(";", "").trim();
                       String[] definitionword=definition.split(" ");
                       if (isNumber(definitionword[0])) {
                           throw new IllegalArgumentException("Cannot redefine numbers");
                       }else{
                           List<String> comandosList=new ArrayList<>();                       
                           for (String string : definitionword) {
                               comandosList.add(string);                               
                           }
                           comandosList.remove(0);                           
                           
                           for (int j = 0; j < comandosList.size(); j++) {
                              
                               if (wordDefinitions.containsKey(comandosList.get(j).toUpperCase())) {
                                   List<String> temp=wordDefinitions.get(comandosList.get(j).toUpperCase());                                  
                                   
                                   for (int k = temp.size()-1; k >=0; k--) {
                                       comandosList.set(j,temp.get(k));
                                   } 
                               }
                           }
                           defineWord(definitionword[0], comandosList);
                       }
                   } 
               }
              
            String[] tokens = input.get(input.size()-1).split("\\s+");
            for (String token : tokens) {
                evaluateToken(token);
            }

        return this.stack;
    }

    private void evaluateToken(String token) {
        if (isNumber(token)) {
            int number = Integer.parseInt(token);
            stack.push(number);
        } else if (wordDefinitions.containsKey(token.toUpperCase())) {
            // Evaluate the defined word
            List<String> definition = wordDefinitions.get(token.toUpperCase());
            for (String defToken : definition) {
                evaluateToken(defToken);
            }
        } else {
            evaluateWord(token);
        }
    }

    private void evaluateWord(String word) {

        switch (word.toUpperCase()) {
            case "+" -> {
                if (stack.size() >= 2) {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a + b);
                } else {
                    throw new IllegalArgumentException("Addition requires that the stack contain at least 2 values");
                }
            }
            case "-" -> {
                if (stack.size() >= 2) {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                } else {
                    throw new IllegalArgumentException("Subtraction requires that the stack contain at least 2 values");
                }
            }
            case "*" -> {
                if (stack.size() >= 2) {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a * b);
                } else {
                    throw new IllegalArgumentException("Multiplication requires that the stack contain at least 2 values");
                }
            }
            case "/" -> {
                if (stack.size() >= 2) {
                    int b = stack.pop();
                    if (b == 0) {
                        throw new IllegalArgumentException("Division by 0 is not allowed");
                    }
                    int a = stack.pop();
                    stack.push(a / b);
                } else {
                    throw new IllegalArgumentException("Division requires that the stack contain at least 2 values");
                }
            }
            case "DUP" -> {
                if (!stack.isEmpty()) {
                    int top = stack.peek();
                    stack.push(top);
                } else {
                    throw new IllegalArgumentException("Duplicating requires that the stack contain at least 1 value");
                }
            }
            case "DROP" -> {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    throw new IllegalArgumentException("Dropping requires that the stack contain at least 1 value");
                }
            }
            case "SWAP" -> {
                if (stack.size() >= 2) {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(b);
                    stack.push(a);
                } else {
                    throw new IllegalArgumentException("Swapping requires that the stack contain at least 2 values");
                }
            }
            case "OVER" -> {
                if (stack.size() >= 2) {
                    int b = stack.pop();
                    int a = stack.peek();
                    stack.push(b);
                    stack.push(a);
                } else {
                    throw new IllegalArgumentException("Overing requires that the stack contain at least 2 values");
                }
            }
            default ->
                throw new IllegalArgumentException("No definition available for operator \"" + word + "\"");
        }
    }

    public void defineWord(String name, List<String> definition) {
        wordDefinitions.put(name.toUpperCase(), definition);
    }

    private boolean isNumber(String token) {
        return token.matches("-?\\d+");
    }

}
