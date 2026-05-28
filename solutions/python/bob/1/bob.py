def response(input_text):
    text = input_text.strip()

    if not text:
        return "Fine. Be that way!"

    letters = [c for c in text if c.isalpha()]
    has_letters = bool(letters)
    is_yell = has_letters and all(c.isupper() for c in letters)
    is_question = text.endswith('?')

    if is_yell and is_question:
        return "Calm down, I know what I'm doing!"
    if is_question:
        return "Sure."
    if is_yell:
        return "Whoa, chill out!"

    return "Whatever."