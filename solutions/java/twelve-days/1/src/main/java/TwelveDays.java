class TwelveDays {
     private String conet1="On the ";
     private String conet2=" day of Christmas my true love gave to me: ";
     private String conet3="a Partridge in a Pear Tree.\n";
     private String[] conet4 = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"};
    private String[] conect5={"and a Partridge in a Pear Tree.\n","two Turtle Doves, ","three French Hens, ","four Calling Birds, ","five Gold Rings, ","six Geese-a-Laying, ","seven Swans-a-Swimming, ","eight Maids-a-Milking, ","nine Ladies Dancing, ","ten Lords-a-Leaping, ","eleven Pipers Piping, ","twelve Drummers Drumming, "};
    private String c="and";
    
    
    String verse(int verseNumber) {
        StringBuilder resultado = new StringBuilder();
        StringBuilder resultado2 = new StringBuilder();
        int control=verseNumber-1;
        if(verseNumber==1){
            resultado.append(conet1).append(conet4[0]).append(conet2).append(conet3);                     
        }else {
            for(int i = 1; i <= verseNumber; i++){
                resultado2.append(conect5[control]);
                control--;
            }           
           resultado2.toString();
           
           resultado.append(conet1).append(conet4[verseNumber-1]).append(conet2).append(resultado2);
        }
        return resultado.toString();
        
    }

    String verses(int startVerse, int endVerse) {
        StringBuilder res = new StringBuilder();
        for(int j = startVerse; j <= endVerse; j++){
            if(j<endVerse){
              res.append(verse(j)).append("\n");  
            }else
            {
             res.append(verse(j));
            }
            
        }
        return res.toString();
        
    }
    
    String sing() {
        StringBuilder cancion = new StringBuilder();
        for(int c=1; c<=12;c++){
            if(c<12){
              cancion.append(verse(c)).append("\n");  
            }else
            {
             cancion.append(verse(c));
            }
            
        }
        return cancion.toString();
    }
    
}
