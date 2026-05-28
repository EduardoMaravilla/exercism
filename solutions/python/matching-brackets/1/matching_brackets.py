def is_paired(input_string):
    stack = []
    pairs = {")": "(", "]": "[", "}": "{"}

    for ch in input_string:
        if ch in pairs.values():
            stack.append(ch)
        elif ch in pairs and (not stack or stack.pop() != pairs[ch]):
            return False

    return not stack