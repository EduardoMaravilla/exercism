public class Hamming {
    private final String chain1;
    private final String chain2;
    public Hamming(String leftStrand, String rightStrand) {
        if (leftStrand.length() != rightStrand.length()) {
            throw new IllegalArgumentException("strands must be of equal length");
        }
        chain1 = leftStrand;
        chain2 = rightStrand;
    }

    public int getHammingDistance() {        
        int haDistance = 0;
        for (int i = 0; i < chain1.length(); i++) {            
            if (chain1.charAt(i) != chain2.charAt(i)) {
                haDistance++;
            }
        }
        return haDistance;
    }
}