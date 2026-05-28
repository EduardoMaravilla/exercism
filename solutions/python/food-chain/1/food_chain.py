def recite(start_verse, end_verse):
    animals = [
        "fly",
        "spider",
        "bird",
        "cat",
        "dog",
        "goat",
        "cow",
        "horse",
    ]

    descriptions = [
        "It wriggled and jiggled and tickled inside her.",
        "How absurd to swallow a bird!",
        "Imagine that, to swallow a cat!",
        "What a hog, to swallow a dog!",
        "Just opened her throat and swallowed a goat!",
        "I don't know how she swallowed a cow!",
    ]

    chain = [
        "She swallowed the spider to catch the fly.",
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
        "She swallowed the cat to catch the bird.",
        "She swallowed the dog to catch the cat.",
        "She swallowed the goat to catch the dog.",
        "She swallowed the cow to catch the goat.",
    ]

    ending = "I don't know why she swallowed the fly. Perhaps she'll die."

    result = []

    for verse in range(start_verse, end_verse + 1):
        if verse > start_verse:
            result.append("")
        result.append(f"I know an old lady who swallowed a {animals[verse - 1]}.")

        if verse == 8:
            result.append("She's dead, of course!")
            continue

        if verse > 1:
            result.append(descriptions[verse - 2])

        for i in range(verse - 2, -1, -1):
            result.append(chain[i])

        result.append(ending)

    return result

