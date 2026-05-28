

public class QueenAttackCalculator{
    private Queen queen1;
    private Queen queen2;

    public QueenAttackCalculator(Queen queen1,Queen queen2){
        if ( queen1==null) {
            throw new IllegalArgumentException("You must supply valid positions for both Queens.");
              }
        if ( queen2==null) {
            throw new IllegalArgumentException("You must supply valid positions for both Queens.");
              }
        if(queen2.x()==queen1.x() && queen2.y()==queen1.y()){
           throw new IllegalArgumentException("Queens cannot occupy the same position.");
        }
        
            this.queen1 = queen1;
            this.queen2 = queen2;
    }
    
    boolean canQueensAttackOneAnother(){
        if(queen2.x()==queen1.x()){
           return true; 
        }else if(queen2.y()==queen1.y()){
            return true;
        }else if(Math.abs(queen1.x() - queen2.x()) == Math.abs(queen1.y() - queen2.y())){
            return true;
        }
        return false;
        
    }

    
}

class Queen{
         private int x;
         private int y;

         public Queen(int x, int y){
             if ( x > 7 ) {
            throw new IllegalArgumentException("Queen position must have row <= 7.");
              }
             if ( y > 7) {
            throw new IllegalArgumentException("Queen position must have column <= 7.");
              }
             if ( x < 0) {
            throw new IllegalArgumentException("Queen position must have positive row.");
              }
             if ( y < 0) {
            throw new IllegalArgumentException("Queen position must have positive column.");
              }
             
            this.x = x;
            this.y = y; 
             }
         
             public int x() {
             return x;
             }
             public int y() {
              return y;
            }
         
}

    
    
