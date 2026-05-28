public class FoodChain{
     private String verso1="I don't know why she swallowed the fly. Perhaps she'll die.";
    
     private String verso2="I know an old lady who swallowed a ";
    
     private String[] animal={"fly.\n","spider.\n","bird.\n","cat.\n","dog.\n","goat.\n","cow.\n","horse.\n"};
    
     private String[] desAnimal={"It wriggled and jiggled and tickled inside her.\n","How absurd to swallow a bird!\n","Imagine that, to swallow a cat!\n","What a hog, to swallow a dog!\n","Just opened her throat and swallowed a goat!\n","I don't know how she swallowed a cow!\n"};
    
     private String[] verso4={"She swallowed the spider to catch the fly.\n","She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n","She swallowed the cat to catch the bird.\n","She swallowed the dog to catch the cat.\n","She swallowed the goat to catch the dog.\n","She swallowed the cow to catch the goat.\n"};
    
     private String verso5="She's dead, of course!";

    public String verses(int startverse,int endverse){
        String versos="";
        for(int j=startverse;j<=endverse;j++){
            versos=versos+verse(j);
            if(j!=endverse){
            versos=versos+"\n\n";
            }
        }
        return versos;
    }      
    public String verse(int ver){ 
        String frase="";
        String temporal="";
         if(ver==1){
             frase=verso2+animal[ver-1]+verso1;
             return frase;
         }else if(ver==8){
             frase=verso2+animal[ver-1]+verso5;
             return frase;
         }else{
             for(int i=0;i<=(ver-2);i++){
                 temporal=verso4[i]+temporal;
             }
             frase=verso2+animal[ver-1]+desAnimal[ver-2]+temporal+verso1;
             return frase;
         }
        
        
    }
}