def commands(binary_str):
    actions = ["wink", "double blink", "close your eyes", "jump"]
    result = [action for i, action in enumerate(actions) if binary_str[4 - i] == "1"]
    if binary_str[0] == "1":
        result.reverse()
    return result