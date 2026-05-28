class Proverb {
    String[] words;    
    Proverb(String[] words) {
        this.words = words;
    }
    String recite() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            sb.append("For want of a " + words[i] + " the " + words[i+1] + " was lost.\n");
        }
        if (words.length > 0) {
            sb.append("And all for the want of a " + words[0] + ".");
        }
        return sb.toString();
    }
}