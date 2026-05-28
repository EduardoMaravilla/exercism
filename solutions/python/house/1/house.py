PARTS = [
    "the house that Jack built.",
    "the malt that lay in ",
    "the rat that ate ",
    "the cat that killed ",
    "the dog that worried ",
    "the cow with the crumpled horn that tossed ",
    "the maiden all forlorn that milked ",
    "the man all tattered and torn that kissed ",
    "the priest all shaven and shorn that married ",
    "the rooster that crowed in the morn that woke ",
    "the farmer sowing his corn that kept ",
    "the horse and the hound and the horn that belonged to ",
]


def verse(n: int) -> str:
    """Return verse `n` (1-based) of 'This is the House that Jack Built'."""
    idx = n - 1
    line = "This is " + PARTS[idx]
    for i in range(idx - 1, -1, -1):
        line += PARTS[i]
    return line


def recite(start_verse: int, end_verse: int) -> list[str]:
    """Return verses from `start_verse` to `end_verse` inclusive."""
    return [verse(n) for n in range(start_verse, end_verse + 1)]
