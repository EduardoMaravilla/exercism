def proverb(*args, qualifier=None):
    words=[*args]
    result = []
    for i in range(len(words)-1):
        result.append(f"For want of a {words[i]} the {words[i + 1]} was lost.")

    if len(words) > 0:
        last_word = f"{qualifier} {words[0]}" if qualifier is not None else f"{words[0]}"
        result.append(f"And all for the want of a {last_word}.")

    return result
