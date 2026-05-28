class RnaTranscription {

    String transcribe(String dnaStrand) {
        StringBuilder rna = new StringBuilder();
        int num = dnaStrand.length();
        for (int i = 0; i < num; i++) {
            String val = dnaStrand.substring(i, i + 1);
            if (val.equals("G")) {
                rna.append("C");
            } else if (val.equals("C")) {
                rna.append("G");
            } else if (val.equals("T")) {
                rna.append("A");
            } else if (val.equals("A")) {
                rna.append("U");
            }
        }
        return rna.toString();
    }
}
