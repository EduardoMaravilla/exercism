public class Hamming {
         private String Cadena1;
         private String Cadena2;
    
    public Hamming(String leftStrand, String rightStrand) {
        Cadena1=leftStrand;
        Cadena2=rightStrand;
        if(leftStrand.isEmpty() && rightStrand.length()>0){
            throw new IllegalArgumentException("left strand must not be empty.");
        }else if(rightStrand.isEmpty() && leftStrand.length()>0){
            throw new IllegalArgumentException("right strand must not be empty.");
        }else if(leftStrand.length()!=rightStrand.length()){
            throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
        }     
    }

    public int getHammingDistance() {
        int largo=Cadena1.length();
        int hadistan=0;
        
        if(largo==0){
            return 0;
        }
        for(int i=0;i<largo;i++){
            char c=Cadena1.charAt(i);
            char d=Cadena2.charAt(i);
            if(c!=d){
                hadistan++;
            }
        }

        return hadistan;
    }
}
