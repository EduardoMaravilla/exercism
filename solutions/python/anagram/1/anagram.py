def find_anagrams(word, candidates):
    return [anagram for anagram in candidates if is_anagram(word,anagram)]

def is_anagram(word1,word2):
    if len(word1) != len(word2):
        return False
    elif word1.lower() == word2.lower():
        return False
    else:
        return "".join(sorted(word1.lower())) == "".join(sorted(word2.lower()))