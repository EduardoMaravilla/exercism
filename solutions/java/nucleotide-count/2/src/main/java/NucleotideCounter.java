import java.util.HashMap;
import java.util.Map;

public class NucleotideCounter {
    private final String chain;

    public NucleotideCounter(String chain) {
        this.chain = chain;
        if ( chain.matches(".*[^ACGT].*")) {
            throw new IllegalArgumentException();
        }
    }
    public Map<Character, Integer> nucleotideCounts() {

        Map<Character, Integer> NucleotideCount = new HashMap<>();
        NucleotideCount.put('A', 0);
        NucleotideCount.put('C', 0);
        NucleotideCount.put('G', 0);
        NucleotideCount.put('T', 0);

        for (int j = 0; j < chain.length(); j++) {
            char d = chain.charAt(j);
            Integer count = NucleotideCount.get(d);
            NucleotideCount.put(d, count + 1);
        }
        return NucleotideCount;
    }
}