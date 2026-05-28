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
        List<Integer> numbers = new ArrayList<>();
        List<String> motion = new ArrayList<>();
        if (operations.length == 1) {
            return Integer.parseInt(operations[0]);
        }

        for (int i = 0; i < operations.length; i++) {
            try {
                int num=Integer.parseInt(operations[i]);
                numbers.add(num);

            } catch (Exception e) {
                if (i % 2 == 0) throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
                if (operations[i].equals("plus")) motion.add(operations[i]);
                if (operations[i].equals("minus")) motion.add(operations[i]);
                if (operations[i].equals("multiplied")) motion.add(operations[i]);
                if (operations[i].equals("divided")) motion.add(operations[i]);
                if (operations[i].equals("cubed")) motion.add(operations[i]);
            }
        }      

        if (!(numbers.size() == motion.size() + 1)) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }

        for (int i=0;i<motion.size();i++) {
            String string=motion.get(i);
            switch (string) {
                case "plus" -> {
                    if (i == 0) {
                        respuesta = numbers.get(i) + numbers.get(i + 1);
                    } else {
                        respuesta += numbers.get(i + 1);
                    }
                }
                case "minus" -> {
                    if (i == 0) {
                        respuesta = numbers.get(i) - numbers.get(i + 1);
                    } else {
                        respuesta -= numbers.get(i + 1);
                    }
                }
                case "multiplied" -> {
                    if (i == 0) {
                        respuesta = numbers.get(i) * numbers.get(i + 1);
                    } else {
                        respuesta *= numbers.get(i + 1);
                    }
                }
                case "divided" -> {
                    if (i == 0) {
                        respuesta = numbers.get(i) / numbers.get(i + 1);
                    } else {
                        respuesta /= numbers.get(i + 1);
                    }
                }
                default -> throw new AssertionError();
            }
        }
        return respuesta;
    }
}