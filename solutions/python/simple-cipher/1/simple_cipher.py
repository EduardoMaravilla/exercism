from random import choice
import string

ALPHABET = string.ascii_lowercase


class Cipher:
    def __init__(self, key=None):
        if key is None:
            self._key = "".join(choice(ALPHABET) for _ in range(100))
        else:
            self._key = key

    def _extend_key(self, text):
        return (self._key * (len(text) // len(self._key) + 1))[:len(text)]

    def encode(self, text):
        key = self._extend_key(text)
        result = []

        for t, k in zip(text, key):
            val = (ALPHABET.index(t) + ALPHABET.index(k)) % 26
            result.append(ALPHABET[val])

        return "".join(result)

    def decode(self, text):
        key = self._extend_key(text)
        result = []

        for t, k in zip(text, key):
            val = (ALPHABET.index(t) - ALPHABET.index(k)) % 26
            result.append(ALPHABET[val])

        return "".join(result)

    @property
    def key(self):
        return self._key