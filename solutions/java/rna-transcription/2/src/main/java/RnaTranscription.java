class RnaTranscription {
    String transcribe(String dnaStrand) {
        StringBuilder rna = new StringBuilder();
        for (char nucleotide : dnaStrand.toCharArray()) {
            switch (nucleotide) {
                case 'G':
                    rna.append('C');
                    break;
                case 'C':
                    rna.append('G');
                    break;
                case 'T':
                    rna.append('A');
                    break;
                case 'A':
                    rna.append('U');
                    break;
                default:
                    throw new IllegalArgumentException("Nucleótido inválido: " + nucleotide);
            }
        }
        return rna.toString();
    }
}

