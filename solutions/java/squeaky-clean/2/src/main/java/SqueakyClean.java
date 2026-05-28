class SqueakyClean {

    private final static String[] leetspeak = {"4", "3", "0", "1", "7"};
    private final static String[] speak = {"a", "e", "o", "l", "t"};

    static String clean(String identifier) {

        identifier = identifier.replace(" ", "_");        
        identifier = kebabCaseToCamelCase(identifier);
        identifier = leetpeakToSpeak(identifier);
        identifier = identifier.replaceAll("[^a-zA-Z_]","");
        return identifier;
    }

    private static String leetpeakToSpeak(String identifier) {
        String[] texts = identifier.split("");
        for (int i = 0; i < texts.length; i++) {
            for (int j = 0; j < leetspeak.length; j++) {
                if (texts[i].equals(leetspeak[j])) {
                    texts[i] = speak[j];
                    break;
                }
            }
        }
        identifier = String.join("", texts);
        return identifier;
    }

    private static String kebabCaseToCamelCase(String identifier) {
        String[] texts = identifier.split("-");
        StringBuilder sb = new StringBuilder(texts[0]);
        for (int i = 1; i < texts.length; i++) {
            sb.append(texts[i].substring(0, 1).toUpperCase())
                    .append(texts[i].substring(1));
        }
        identifier = sb.toString();        
        return identifier;
    }

}


