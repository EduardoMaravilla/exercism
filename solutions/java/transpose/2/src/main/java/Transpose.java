public class Transpose {

    public String transpose(String toTranspose) {
        toTranspose = toTranspose.replace(" ", "*");
        StringBuilder transpose = new StringBuilder();
        String[] chains = toTranspose.split("\n");
        int control = chains[0].length();
        for (String chain : chains) {
            if (chain.length() > control) {
                control = chain.length();
            }
        }
        for (int i = 0; i < chains.length; i++) {
            if (chains[i].length() < control) {
                int val = control - chains[i].length();
                for (int j = 0; j < val; j++) {
                    chains[i] = chains[i] + " ";
                }
            }
        }
        for (int i = 0; i < control; i++) {
            for (String chain : chains) {
                transpose.append(chain.charAt(i));
            }
            transpose = new StringBuilder(transpose.toString().trim());
            if (i < control - 1) {
                transpose.append("\n");
            }
        }
        return transpose.toString().trim().replace("*", " ");
    }
}