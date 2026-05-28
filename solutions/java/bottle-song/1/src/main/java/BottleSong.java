public class BottleSong {

    String recite(int startBottles, int takeDown) {
        StringBuilder song = new StringBuilder();
        for (int i = startBottles; i > (startBottles - takeDown); i--) {
            song.append(verse(i));
            if(takeDown > 1 && i-1 != (startBottles - takeDown)) song.append("\n");
        }
        return song.toString();
    }

    private String verse(int ver) {
        String[] number = {"no", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
        String phrase1 = "%s green bottle"+(ver != 1? "s":"") +" hanging on the wall,\n";
        String phrase2 = "And if one green bottle should accidentally fall,\n";
        String phrase3 = "There'll be %s green bottle"+(ver-1 != 1? "s":"") +" hanging on the wall.\n";
        String vers = String.format(phrase1, number[ver]).repeat(2) + phrase2 + String.format(phrase3, number[ver - 1].toLowerCase());
        return vers;
    }
}