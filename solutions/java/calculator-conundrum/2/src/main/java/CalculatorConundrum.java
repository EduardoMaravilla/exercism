public class CalculatorConundrum {
    public String calculate(int x, int y, String operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }
        if (operation.isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be empty");
        }

        int result;
        switch (operation) {
            case "+" -> result = x + y;
            case "*" -> result = x * y;
            case "/" -> {
                if (y == 0) {
                    throw new IllegalOperationException("Division by zero is not allowed", new ArithmeticException("Division by zero"));
                }
                result = x / y;
            }
            default -> throw new IllegalOperationException("Operation '" + operation + "' does not exist");
        }

        return x + " " + operation + " " + y + " = " + result;
    }
}