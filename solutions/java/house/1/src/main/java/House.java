class House{
    private String verso1="This is ";
    private String verso2="the house that Jack built.";
    private String[] versos={"the malt that lay in ","the rat that ate ","the cat that killed ","the dog that worried ","the cow with the crumpled horn that tossed ","the maiden all forlorn that milked ","the man all tattered and torn that kissed ","the priest all shaven and shorn that married ","the rooster that crowed in the morn that woke ","the farmer sowing his corn that kept ","the horse and the hound and the horn that belonged to "};
    
    String verse(int num){
        StringBuilder frase= new StringBuilder();
        StringBuilder frases= new StringBuilder();
        String cadena;
        String subcadena="";
        if(num==1){
          frase.append(verso1).append(verso2);
            return frase.toString();
        }             
        else{           
            for(int i=1; i<=(num-1);i++){                
               subcadena=subcadena.replaceFirst("^",versos[i-1]);
             }
            frases.append(verso1).append(subcadena).append(verso2);
            return frases.toString();
            
        }   
    }
    
    String verses(int num1,int num2){
        StringBuilder fras= new StringBuilder();
        if(num1>num2){
            return "Digite bien la secuencia de menor a mayor";
        }else if(num1==num2){
            return verse(num1);
        } else {
            for(int j=num1; j<=num2;j++){
                fras.append(verse(j));
                if(j<num2){
                    fras.append("\n");
                }
            }
            return fras.toString();   
        }
        
        
    }
    String sing(){        
        return verses(1,12);
    }
}