import java.util.List;
import java.util.ArrayList;

class ProteinTranslator {
    List<String> translate(String rnaSequence) {
        List<String> proteins = new ArrayList<>();
        int cod = 3;
        int difference= rnaSequence.length() % 3;
        if( difference > 0) {
            for (int i = 0; i < 3 - difference; i++) {
                rnaSequence = rnaSequence.concat("O");
            }
        }
        int totalRna = rnaSequence.length();
        int numberOfCodon =  totalRna / cod;
        for (int i = 0; i < numberOfCodon; i++) {
            String prot = protein(rnaSequence.substring(i * cod, (i + 1) * cod));
            if (prot.equals("STOP")) {
                break;
            }
            proteins.add(prot);
        }
        return proteins;
    }
    private String protein(String Codon) {

        return switch (Codon) {
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