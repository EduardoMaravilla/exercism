def answer(question):
    if not question.startswith("What is"):
        raise ValueError("unknown operation")

    q = question.replace("What is", "").replace("?", "").strip()

    if not q:
        raise ValueError("syntax error")

    q = (q.replace("plus", "+")
           .replace("minus", "-")
           .replace("multiplied by", "*")
           .replace("divided by", "/"))

    tokens = q.split()

    for token in tokens:
        if token not in {"+", "-", "*", "/"}:
            try:
                int(token)
            except:
                raise ValueError("unknown operation")

    if len(tokens) % 2 == 0:
        raise ValueError("syntax error")

    for i in range(0, len(tokens), 2):
        try:
            int(tokens[i])
        except:
            raise ValueError("syntax error")

    result = int(tokens[0])

    for i in range(1, len(tokens), 2):
        op = tokens[i]
        num = int(tokens[i + 1])

        if op == "+":
            result += num
        elif op == "-":
            result -= num
        elif op == "*":
            result *= num
        elif op == "/":
            result //= num

    return result
