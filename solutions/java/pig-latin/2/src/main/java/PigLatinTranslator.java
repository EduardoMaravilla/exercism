public class PigLatinTranslator{
     public static String  translate(String word){
         String[] Palabras=word.split("\\s+");
         StringBuilder resultado = new StringBuilder();
         for (String control:Palabras){
             if(sonidovocal(control.charAt(0), control.charAt(1))){
                resultado.append(control).append("ay ");
             }else if(control.startsWith("thr")){
                 resultado.append(control.substring(3)).append("thray ");
             }else if(control.startsWith("ch")){
                 resultado.append(control.substring(2)).append("chay ");
             }else if(control.startsWith("rh")){
                 resultado.append(control.substring(2)).append("rhay ");
             }else if(control.startsWith("th")){
                 resultado.append(control.substring(2)).append("thay ");
             }else if(control.startsWith("qu")){
                 resultado.append(control.substring(2)).append("quay ");
             }else if(control.startsWith("squ")){
                 resultado.append(control.substring(3)).append("squay ");
             }else if(control.startsWith("sch")){
                 resultado.append(control.substring(3)).append("schay ");
             }else{
                 resultado.append(control.substring(1)).append(control.charAt(0)).append("ay ");
             }
         }
         return resultado.toString().trim();
     }
    public static boolean sonidovocal(char c, char c2){
        if("aeiou".indexOf(c)!=-1){
            return true;            
        }else if("xy".indexOf(c)!=-1){
            if("aeiou".indexOf(c2)!=-1){
               return false; 
            }else{
                return true;
            }             
        }else {
            return false;
        }        
    }        
}