#include "matching_brackets.h"

#include <stack>

namespace matching_brackets {

    static bool isOpen(const char c) {
        return c == '(' || c == '[' || c == '{';
    }

    static bool isClosing(const char c) {
        return c == ')' || c == ']' || c == '}';
    }

    static bool isPair(const char c1, const char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || ( c1 == '{' && c2 == '}');
    }

    bool check(const std::string &str) {

        std::stack<char> stack;
        for (const char& c : str) {
            if (isOpen(c)) {
                stack.push(c);
            }else if (isClosing(c)) {
                if (stack.empty() || !isPair(stack.top(), c)) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.empty();
    }
} // namespace matching_brackets
