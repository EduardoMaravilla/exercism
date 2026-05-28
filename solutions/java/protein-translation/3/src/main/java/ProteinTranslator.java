import java.util.ArrayList;
import java.util.List;
class ProteinTranslator {
    List<String> translate(String rnaSequence) {
        List<String> proteins = new ArrayList<>();
        int codonLength = 3;
        int sequenceLength = rnaSequence.length();
        int difference = sequenceLength % codonLength;
        if (difference > 0) {
            rnaSequence += "O".repeat(3 - difference);
        }
        for (int i = 0; i < sequenceLength; i += codonLength) {
            String codon = rnaSequence.substring(i, i + codonLength);
            String protein = protein(codon);
            if ("STOP".equals(protein)) {
                break;
            }
            proteins.add(protein);
        }
        return proteins;
    }
    private String protein(String codon) {
        return switch (codon) {
            case "AUG" -> "Methionine";
            case "UUU", "UUC" -> "Phenylalanine";
            case "UUA", "UUG" -> "Leucine";
            case "UCU", "UCC", "UCA", "UCG" -> "Serine";
            case "UAU", "UAC" -> "Tyrosine";
            case "UGU", "UGC" -> "Cysteine";
            case "UGG" -> "Tryptophan";
            case "UAA", "UAG", "UGA" -> "STOP";
            default -> throw new IllegalArgumentException("Invalid codon");
        };
    }
}