class Acronym {
    private String frase;

    Acronym(String phrase) {
        frase = phrase;
    }
    String get() {
        StringBuilder acr = new StringBuilder();
        String[] cadena = frase.split("[\\s-]+");
        int num = cadena.length;
        for(int i = 0; i < num; i++) {
            String primer = cadena[i];
            primer = primer.replaceAll("[^a-zA-Z]", "");
            acr.append(primer.substring(0, 1));
        }
        return acr.toString().toUpperCase();
    }
}
