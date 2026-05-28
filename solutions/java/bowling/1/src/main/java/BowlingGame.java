import java.util.ArrayList;
import java.util.List;

class BowlingGame {
   private List<Integer> rolls = new ArrayList<>();
   private boolean hasBonusRoll = true;
   private int frames=0;
   private boolean frame=false;
   private int puntos=0;
    private int cuadro=0;
    private int bonus2=0;
    private boolean chuza =false;

   void roll(int pins) {
       rolls.add(pins);
       frame=false;
       if(frames==10 && hasBonusRoll==false){
        throw new IllegalStateException("Cannot roll after game is over");
       }
        if(frames==10 && hasBonusRoll==true && chuza==false){
            bonus2++;
            if(bonus2==2){
               throw new IllegalStateException("Cannot roll after game is over"); 
            }  
       }else if(frames==10 && hasBonusRoll==true && chuza==true){
            bonus2++;
            if(pins<10){
                puntos=puntos+pins;
            }else if(puntos>0){
                puntos=puntos+pins;
            } 
            if(puntos>10){
              throw new IllegalStateException("Pin count exceeds pins on the lane"); 
            }
            if(bonus2==3){
               throw new IllegalStateException("Cannot roll after game is over"); 
            }
       }
        
       if(pins<0){
         throw new IllegalStateException("Negative roll is invalid");  
       }
       if(pins>10){
         throw new IllegalStateException("Pin count exceeds pins on the lane");  
       }
       if(pins==10 && cuadro==0 && frames<10){
           frame=true;
           frames++;
           chuza=true;
       }
       if(pins<10 && frame==false && frames <10){
           puntos=puntos+pins;
           cuadro++;
           if(puntos>10){
              throw new IllegalStateException("Pin count exceeds pins on the lane"); 
           }
           if(cuadro==2){
               frames++;
               if(frames==10 && puntos==10){
                   hasBonusRoll=true;
                   chuza=false;
               }else if(frames==10 && puntos<10){
                   hasBonusRoll=false;
               }
               puntos=0;
               cuadro=0;            
           }
           
       }     
   } 

   int score() {
      if(frames<10 || (hasBonusRoll==true && bonus2==0 && chuza==false) || (hasBonusRoll==true && bonus2<2 && chuza==true)){
         throw new IllegalStateException("Score cannot be taken until the end of the game");  
       }
       int suma=0;
       for(int i=0;i<rolls.size();i++){
           suma += rolls.get(i);
       }
       if(suma==0){
          return 0; 
       }
       boolean calcular=true;
       int num=rolls.size();
       int valor1=0;
       int valor2=0;
       int valor3=0;
       int cuadros=frames;
       suma=0;
       for(int k=0;k<num;k++){
            if(cuadros==1){
               if((num-k)==2){
                   suma=suma+rolls.get(k)+rolls.get(k+1);
                   k=rolls.size();
               }else if((num-k)==3){
                   suma=suma+rolls.get(k)+rolls.get(k+1)+rolls.get(k+2);
                   k=rolls.size();
               }
               
           }
           
           if(cuadros>1){
            valor1=rolls.get(k);
           valor2=rolls.get(k+1);
           valor3=rolls.get(k+2);  
           }
           if(valor1==10 && cuadros>1){
               suma=suma+valor1+valor2+valor3;
               cuadros--;
           }else if((valor1+valor2)==10 && cuadros>1){
               suma=suma+valor1+valor2+valor3;
               k += 1;
               cuadros--;
           }else if((valor1+valor2)<10 && cuadros>1){
               suma=suma+valor1+valor2;
               k += 1;
               cuadros--;
           }
           
       }
           
       
       
       return suma;
       
   }
    
 
}

