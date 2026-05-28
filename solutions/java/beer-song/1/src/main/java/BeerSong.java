class BeerSong{
    private String verso1=" bottles of beer on the wall, ";
    private String verso10=" bottle of beer on the wall, ";
    private String verso2=" bottles of beer.\n";
    private String verso20=" bottle of beer.\n";
    private String verso3="Take one down and pass it around, ";
    private String verso4=" bottles of beer on the wall.\n\n";
    private String verso40=" bottle of beer on the wall.\n\n";
    private String verso5="No more";
    private String verso8="no more";
    private String verso6="Take it down and pass it around, ";
    private String verso7="Go to the store and buy some more, ";
    
       String sing(int verseNumber,int versenext){
        if(verseNumber>99){
            verseNumber=99;
        }
        int control=verseNumber;
        StringBuilder cancion= new StringBuilder();
        
        for(int i=0;i<versenext;i++){
            cancion.append(versos(control));
            control--;
        }
        return cancion.toString();
    }

       String versos(int ver){
        int cont=ver-1;
        StringBuilder frase= new StringBuilder();      
        int cantidad=99;
        if(ver>=3){
           frase.append(ver);
           frase.append(verso1);
           frase.append(ver);
           frase.append(verso2);
           frase.append(verso3);
           frase.append(cont);
           frase.append(verso4); 
        }else if(ver==2){
           frase.append(ver);
           frase.append(verso1);
           frase.append(ver);
           frase.append(verso2);
           frase.append(verso3);
           frase.append(cont);
           frase.append(verso40); 
        }else if(ver==1){
            frase.append(ver);
           frase.append(verso10);
           frase.append(ver);
           frase.append(verso20);
           frase.append(verso6);
           frase.append(verso8);
           frase.append(verso4);
        }else {
            frase.append(verso5);
           frase.append(verso1);
           frase.append(verso8);
           frase.append(verso2);
           frase.append(verso7);
           frase.append(cantidad);
           frase.append(verso4);
        }       

        return frase.toString();
    }
    String singSong(){
        StringBuilder canc= new StringBuilder();
        for(int j=99; j>=0;j-- ){
            canc.append(versos(j));
        }
        return canc.toString();
        
    }
}
