import java.util.Stack;
public class BracketChecker {
    private final String input;
    public BracketChecker(String input) {
        this.input = input;
    }
    public boolean areBracketsMatchedAndNestedCorrectly() {
        Stack<Character> stack = new Stack<>();
        for (char ch : input.toCharArray()) {
            if (isOpenBracket(ch)) {
                stack.push(ch);
            } else if (isCloseBracket(ch)) {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    private boolean isOpenBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }
    private boolean isCloseBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }
    private boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') || (open == '[' && close == ']') || (open == '{' && close == '}');
    }
}