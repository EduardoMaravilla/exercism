def rotate(text, key):
    result = []

    for letter in text:
        if letter.isalpha():
            base = ord('A') if letter.isupper() else ord('a')
            rotated = chr((ord(letter) - base + key) % 26 + base)
            result.append(rotated)
        else:
            result.append(letter)

    return "".join(result)