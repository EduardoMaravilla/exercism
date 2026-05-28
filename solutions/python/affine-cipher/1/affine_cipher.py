import re

PLAIN_ALPHAT="abcdefghijklmnopqrstuvwxyz"
ALPHAT_SIZE=26

def encode(plain_text, a, b):
    if _calculate_gcd(a, ALPHAT_SIZE) != 1:
        raise ValueError("a and m must be coprime.")
    text = re.sub(r'[^a-z0-9]', '', plain_text.lower())
    text_encode = []
    for i in range(len(text)):
        if i > 0 and i % 5 == 0:
            text_encode.append(' ')
        if text[i] in PLAIN_ALPHAT:
            index = (a * PLAIN_ALPHAT.index(text[i]) + b) % ALPHAT_SIZE
            text_encode.append(PLAIN_ALPHAT[index])
        else:
            text_encode.append(text[i])
    return ''.join(text_encode)

def decode(ciphered_text, a, b):
    if _calculate_gcd(a, ALPHAT_SIZE) != 1:
        raise ValueError("a and m must be coprime.")
    inverse_coefficient = _find_multiplicative_inverse(a)
    text = ciphered_text.replace(' ', '').lower()
    text_decode = []
    for i in range(len(text)):
        if text[i] in PLAIN_ALPHAT:
            index = (inverse_coefficient * (PLAIN_ALPHAT.index(text[i]) - b + ALPHAT_SIZE)) % ALPHAT_SIZE
            if index < 0:
                index += ALPHAT_SIZE
            text_decode.append(PLAIN_ALPHAT[index])
        else:
            text_decode.append(text[i])
    return ''.join(text_decode)

def _calculate_gcd(a, b):
    if b == 0:
        return a
    return _calculate_gcd(b, a % b)

def _find_multiplicative_inverse(a):
    for i in range(1, ALPHAT_SIZE):
        if (a * i) % ALPHAT_SIZE == 1:
            return i

    raise ValueError("a and m must be coprime.")