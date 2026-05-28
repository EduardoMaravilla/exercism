import re

def abbreviate(words = ""):
    parts = re.split(r"[\s-]+",words.replace("_",""))
    return "".join(word[0].upper() for word in parts if re.match(r"[a-zA-Z]",word))