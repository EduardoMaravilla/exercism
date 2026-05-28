public class Lasagna {
   
    // TODO: define the 'expectedMinutesInOven()' method
       public int expectedMinutesInOven() {
           return 40;
       }
    // TODO: define the 'remainingMinutesInOven()' method
       public int remainingMinutesInOven(int y){
           return expectedMinutesInOven()- y;
       }
    // TODO: define the 'preparationTimeInMinutes()' method
    public int preparationTimeInMinutes(int c){
           return 2*c;
       }

    // TODO: define the 'totalTimeInMinutes()' method
    public int totalTimeInMinutes(int c, int y){
        return 2*c+y;
    } 
}
