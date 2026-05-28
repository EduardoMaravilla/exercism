def is_isogram(string):
    string = string.lower()
    letters = [c for c in string if c.isalpha()]
    return len(set(letters)) == len(letters)