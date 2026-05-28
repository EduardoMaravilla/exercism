import re

PLAIN = "abcdefghijklmnopqrstuvwxyz0123456789"
CIPHER = "zyxwvutsrqponmlkjihgfedcba0123456789"

ENCODE_TABLE = str.maketrans(PLAIN, CIPHER)
DECODE_TABLE = str.maketrans(CIPHER, PLAIN)


def encode(text=""):
    cleaned = re.sub(r"[^a-z0-9]", "", text.lower())
    encoded = cleaned.translate(ENCODE_TABLE)
    return " ".join(encoded[i:i+5] for i in range(0, len(encoded), 5))


def decode(text):
    cleaned = re.sub(r"[^a-z0-9]", "", text.lower())
    return cleaned.translate(DECODE_TABLE)