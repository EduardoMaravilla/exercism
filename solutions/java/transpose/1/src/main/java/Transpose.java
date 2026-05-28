public class Transpose {

    public String transpose(String toTranspose) {
        toTranspose=toTranspose.replace(" ", "*");
        String traspose="";
        String[] chains = toTranspose.split("\n");     
        int control = chains[0].length();
        for (int i = 0; i < chains.length; i++) {
            if (chains[i].length() > control) {
                control = chains[i].length();
            }
        }
        for (int i = 0; i < chains.length; i++) {
            if (chains[i].length() < control) {
                int val=control-chains[i].length();
                for (int j = 0; j < val; j++) {
                  chains[i]=chains[i]+" ";  
                }
                
            }
        }
        for (int i = 0; i < control; i++) {
             for (int j = 0; j < chains.length; j++) {
                traspose=traspose+chains[j].substring(i, i+1);
            }
             traspose=traspose.trim();
            if (i<control-1) {
                traspose=traspose+"\n";
            }
        }
        return traspose.trim().replace("*", " ");
    }
}

