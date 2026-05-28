import re

def count_words(sentence=""):
    word_count = {}

    sentence = re.sub(r"[^a-zA-Z0-9']+", " ", sentence).lower().strip()

    for word in sentence.split():
        word = word.strip("'")

        if word:
            word_count[word] = word_count.get(word, 0) + 1

    return word_count
