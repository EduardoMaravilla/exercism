def translate(text: str) -> str:
    return ' '.join(translate_word(word) for word in text.split())

def translate_word(word: str) -> str:
    if starts_with_vowel_sound(word):
        return f"{word}ay"
    if word.startswith("squ"):
        return f"{word[3:]}squay"
    if word.startswith("qu"):
        return f"{word[2:]}quay"
    if word.startswith("thr"):
        return f"{word[3:]}thray"
    if word.startswith("sch"):
        return f"{word[3:]}schay"
    if word.startswith("ch"):
        return f"{word[2:]}chay"
    if word.startswith("rh"):
        return f"{word[2:]}rhay"
    if word.startswith("th"):
        return f"{word[2:]}thay"
    return f"{word[1:]}{word[0]}ay"


def starts_with_vowel_sound(word: str) -> bool:
    if not word:
        return False

    first = word[0]
    second = word[1] if len(word) > 1 else ' '

    if first in "aeiou":
        return True
    if first in "xy" and second not in "aeiou":
        return True
    return False