import java.util.List;
import java.util.ArrayList;


class ProteinTranslator {

    List<String> translate(String rnaSequence) {
        List<String> proteins = new ArrayList<>();
        int totalrna=rnaSequence.length();
        int cod=3;
        int numofcodon=totalrna/cod;
        for(int i=0; i<numofcodon;i++){
            String prot=protein(rnaSequence.substring(i*cod,(i+1)*cod));
            if(prot.equals("STOP")){
                break;
            }
            proteins.add(prot);
        }
            
        return proteins;
    }
    private String protein(String Codon){

    switch(Codon){
        case "AUG":
            return "Methionine";
        case "UUU":
        case "UUC":
            return "Phenylalanine";
        case "UUA":
        case "UUG":
            return "Leucine";
        case "UCU":
        case "UCC":
        case "UCA":
        case "UCG":
            return "Serine";
        case "UAU":
        case "UAC":
            return "Tyrosine";
        case "UGU":
        case "UGC":
            return "Cysteine";
        case "UGG":
            return "Tryptophan";
        case "UAA":
        case "UAG":
        case "UGA":
            return "STOP";
        default:
            return "STOP";
    }
 }

}
