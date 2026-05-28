public class House {
    private final String[] verbs = {"lay in", "ate", "killed", "worried", "tossed", "milked", "kissed", "married", "woke", "kept", "belonged to"};
    private final String[] phrase = {"house that Jack built.", "malt", "rat", "cat", "dog", "cow with the crumpled horn", "maiden all forlorn", "man all tattered and torn", "priest all shaven and shorn", "rooster that crowed in the morn", "farmer sowing his corn", "horse and the hound and the horn"};
    String verse(int verse) {
        StringBuilder rhyme = new StringBuilder();
        for (int i = 1; i < verse; i++) {
            rhyme.insert(0,String.format("that %s the %s ",verbs[i-1],phrase[i-1]));
        }
        rhyme.insert(0,String.format("This is the %s ", phrase[verse - 1]));
        return rhyme.toString().trim();
    }

    String verses(int startVerse, int endVerse) {
        StringBuilder rhyme=new StringBuilder();
        for (int i = startVerse; i <=endVerse ; i++) {
            rhyme.append(verse(i));
            if (i != endVerse) rhyme.append("\n");
        }
        return rhyme.toString();
    }

    String sing() {
        return verses(1,12);
    }
}