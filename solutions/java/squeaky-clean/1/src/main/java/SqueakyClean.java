class SqueakyClean {
    static String clean(String identifier) {

        if(identifier.contains("-")){
           StringBuilder  Mayugion= new StringBuilder(identifier);
            int z=0;
            while(z<identifier.length()){
                if(Mayugion.charAt(z)=='-'){
                    z++;
                    if(z<identifier.length()){
                       char c = Mayugion.charAt(z);
                    Mayugion.setCharAt(z, Character.toUpperCase(c)); 
                    }
                }
                z++;
            }
            identifier=Mayugion.toString();
        }

        StringBuilder sb = new StringBuilder();
        for (char c : identifier.toCharArray()) {
            if (Character.isISOControl(c)) {
                sb.append("CTRL");
            } else {
                sb.append(c);
            }
        }
        identifier = sb.toString();       

        identifier=identifier.replaceAll("[\\p{InEmoticons}]", "");                 
        identifier=identifier.replaceAll("[α-ω]","");
        identifier=identifier.replaceAll("[0-9]","");        
        
        if(identifier.contains(" ")){
            identifier=identifier.replace(" ", "_");            
        }
        if(identifier.contains("-")){
            identifier=identifier.replace("-","");            
        }

        return identifier;
    }
}

