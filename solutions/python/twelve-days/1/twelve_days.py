def recite(start_verse, end_verse):
    return [verse(n) for n in range(start_verse, end_verse + 1)]

def verse(n):
    days = [
        "first", "second", "third", "fourth", "fifth", "sixth",
        "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"
    ]

    gifts = [
        "a Partridge in a Pear Tree.",
        "two Turtle Doves, ",
        "three French Hens, ",
        "four Calling Birds, ",
        "five Gold Rings, ",
        "six Geese-a-Laying, ",
        "seven Swans-a-Swimming, ",
        "eight Maids-a-Milking, ",
        "nine Ladies Dancing, ",
        "ten Lords-a-Leaping, ",
        "eleven Pipers Piping, ",
        "twelve Drummers Drumming, "
    ]

    opening = f"On the {days[n-1]} day of Christmas my true love gave to me: "

    if n == 1:
        return opening + gifts[0]

    verse_part = ""
    for i in range(n - 1, 0, -1):
        verse_part += gifts[i]

    verse_part += "and " + gifts[0]

    return opening + verse_part
