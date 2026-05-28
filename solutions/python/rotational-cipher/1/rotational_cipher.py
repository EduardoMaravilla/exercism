def rotate(text, key):
    alphabet = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
    if key in (0,26):
        return text
    result = []
    for letter in text:
        index = alphabet.find(letter.lower())
        if index != -1:
            new_char = alphabet[index + key]
            if letter.isupper():
                result.append(new_char.upper())
            else:
                result.append(new_char)
        else:
            result.append(letter)

    return "".join(result)
