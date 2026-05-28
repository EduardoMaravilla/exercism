
public class NaturalNumber {
    private int number;
    
    public NaturalNumber(int number){
        this.number = number;
        if (number <= 0){
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
    }

    public Classification getClassification() {
        if(number==1){
        return Classification.DEFICIENT;  
        }
        int suma=0;
        for(int i=1;i<number;i++){
            if(number % i==0){
               suma=suma+i; 
            }
        }
        if(suma==number){
            return Classification.PERFECT; 
        }else if(suma>number){
            return Classification.ABUNDANT;
        }else{
            return Classification.DEFICIENT;
        }
        
    }
}
