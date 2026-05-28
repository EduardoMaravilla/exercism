def score(word):
    letter_values = {
        **dict.fromkeys("AEIOULNRST", 1),
        **dict.fromkeys("DG", 2),
        **dict.fromkeys("BCMP", 3),
        **dict.fromkeys("FHVWY", 4),
        **dict.fromkeys("K", 5),
        **dict.fromkeys("JX", 8),
        **dict.fromkeys("QZ", 10),
    }

    total = 0

    for letter in word.upper():
        total += letter_values.get(letter, 0)

    return total
