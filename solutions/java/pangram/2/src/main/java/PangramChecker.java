public class PangramChecker {

    private final String abc = "abcdefghijklmnopqrstuvwxyz";

    public boolean isPangram(String input) {
        String sentence = input.toLowerCase();
        int sum = 0;
        StringBuilder negativa = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String val = sentence.substring(i, i + 1);
            if (abc.contains(val)) {
                if (!negativa.toString().contains(val)) {
                    sum++;
                    negativa.append(val);
                }
            }
        }
        return sum == 26;
    }
}