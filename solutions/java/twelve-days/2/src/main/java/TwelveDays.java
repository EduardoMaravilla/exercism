class TwelveDays {
    private final String[] days = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"};
    private final String[] text4 = {"and a Partridge in a Pear Tree.\n", "two Turtle Doves, ", "three French Hens, ", "four Calling Birds, ", "five Gold Rings, ", "six Geese-a-Laying, ", "seven Swans-a-Swimming, ", "eight Maids-a-Milking, ", "nine Ladies Dancing, ", "ten Lords-a-Leaping, ", "eleven Pipers Piping, ", "twelve Drummers Drumming, "};
    private final String c = "and";
    String verse(int verseNumber) {
        StringBuilder verse1 = new StringBuilder();
        StringBuilder verse2 = new StringBuilder();
        int control = verseNumber - 1;
        String text1 = "On the ";
        String text2 = " day of Christmas my true love gave to me: ";
        if (verseNumber == 1) {
            String text3 = "a Partridge in a Pear Tree.\n";
            verse1.append(text1).append(days[0]).append(text2).append(text3);
        } else {
            for (int i = 1; i <= verseNumber; i++) {
                verse2.append(text4[control]);
                control--;
            }
            verse1.append(text1).append(days[verseNumber - 1]).append(text2).append(verse2);
        }
        return verse1.toString();
    }
    String verses(int startVerse, int endVerse) {
        StringBuilder verses = new StringBuilder();
        for (int j = startVerse; j <= endVerse; j++) {
            if (j < endVerse) {
                verses.append(verse(j)).append("\n");
            } else {
                verses.append(verse(j));
            }
        }
        return verses.toString();
    }

    String sing() {
        StringBuilder song = new StringBuilder();
        for (int c = 1; c <= 12; c++) {
            if (c < 12) {
                song.append(verse(c)).append("\n");
            } else {
                song.append(verse(c));
            }
        }
        return song.toString();
    }
}