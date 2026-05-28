import re


class StackUnderflowError(Exception):
    pass


def evaluate(input_data):
    stack = []
    dictionary = {}

    def is_number(token):
        return re.fullmatch(r"-?\d+", token) is not None

    def require_stack(size):
        if len(stack) < size:
            raise StackUnderflowError("Insufficient number of items in stack")

    def eval_token(token):
        if is_number(token):
            stack.append(int(token))
            return

        upper = token.upper()

        if upper in dictionary:
            for t in dictionary[upper]:
                eval_token(t)
            return

        eval_word(upper)

    def eval_word(word):
        if word == "+":
            require_stack(2)
            stack.append(stack.pop() + stack.pop())

        elif word == "-":
            require_stack(2)
            b = stack.pop()
            a = stack.pop()
            stack.append(a - b)

        elif word == "*":
            require_stack(2)
            stack.append(stack.pop() * stack.pop())

        elif word == "/":
            require_stack(2)
            b = stack.pop()
            a = stack.pop()
            if b == 0:
                raise ZeroDivisionError("divide by zero")
            stack.append(a // b)

        elif word == "DUP":
            require_stack(1)
            stack.append(stack[-1])

        elif word == "DROP":
            require_stack(1)
            stack.pop()

        elif word == "SWAP":
            require_stack(2)
            b = stack.pop()
            a = stack.pop()
            stack.append(b)
            stack.append(a)

        elif word == "OVER":
            require_stack(2)
            stack.append(stack[-2])

        else:
            raise ValueError("undefined operation")

    for line in input_data:
        trimmed = line.strip()

        if trimmed.startswith(":") and trimmed.endswith(";"):
            parts = trimmed[1:-1].strip().split()

            name = parts[0]
            name_upper = name.upper()

            if is_number(name):
                raise ValueError("illegal operation")

            body = parts[1:]

            snapshot = dictionary.copy()

            def expand(tokens):
                result = []
                for token in tokens:
                    upper = token.upper()
                    if upper in snapshot:
                        result.extend(expand(snapshot[upper]))
                    else:
                        result.append(token)
                return result

            dictionary[name_upper] = expand(body)
            continue

        tokens = [t for t in trimmed.split() if t]
        for token in tokens:
            eval_token(token)

    return stack