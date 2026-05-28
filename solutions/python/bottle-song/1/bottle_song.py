def recite(start, take=1):
   song = []
   for i in range(start, start -take, -1):
       song.extend(_verse(i))
       if take > 1 and i - 1 != start - take:
           song.append("")
   return song

def _verse(ver):
    number = ["no", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"]
    return [
        f"{number[ver]} green bottle{"s" if ver != 1 else ""} hanging on the wall,",
        f"{number[ver]} green bottle{"s" if ver != 1 else ""} hanging on the wall,",
        "And if one green bottle should accidentally fall,",
        f"There'll be {number[ver-1].lower()} green bottle{"s" if ver - 1 != 1 else ""} hanging on the wall."
    ]

