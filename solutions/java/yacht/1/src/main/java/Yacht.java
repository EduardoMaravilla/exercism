
import java.util.Arrays;
class Yacht {
    private YachtCategory yachtCategory;
    private int[] dice= new int[5];
    
    Yacht(int[] dice, YachtCategory yachtCategory) {
        this.yachtCategory=yachtCategory;
        this.dice=dice;
        Arrays.sort(dice);
    }

    int score() {

        switch(yachtCategory){
            case YACHT:
                 int control=0;
                 for(int i=0;i<4;i++){
                     if(dice[i]==dice[i+1]){
                         control++;
                     }
                 }
                if(control==4){
                   return 50; 
                }else{
                   return 0; 
                }
            case ONES:
                int unos=0;
                for(int i=0;i<5;i++){
                     if(dice[i]==1){
                         unos++;
                     }
                    }
                return unos;
            case TWOS:
                int dos=0;
                for(int i=0;i<5;i++){
                     if(dice[i]==2){
                         dos+=2;
                     }
                    }
                return dos;
            case THREES:
                int tres=0;
                for(int i=0;i<5;i++){
                     if(dice[i]==3){
                         tres+=3;
                     }
                    }
                return tres;
            case FOURS:
                int cuatro=0;
                for(int i=0;i<5;i++){
                     if(dice[i]==4){
                         cuatro+=4;
                     }
                    }
                return cuatro;
            case FIVES:
                int cinco=0;
                for(int i=0;i<5;i++){
                     if(dice[i]==5){
                         cinco+=5;
                     }
                    }
                return cinco;
            case SIXES:
                int seis=0;
                for(int i=0;i<5;i++){
                     if(dice[i]==6){
                         seis+=6;
                     }
                    }
                return seis;
            case FULL_HOUSE:
                int full1=0;
                int full2=0;
                int house=0;
                int con=0;
                int num1=0;
                int num2=0;
                for(int i=0;i<4;i++){
                     if(i==0){
                        num1=dice[i]; 
                     }
                     if(dice[i]!=dice[i+1]&& con==0){
                         num2=dice[i+1];
                         con=1;
                     }
                     if(num1==dice[i] && dice[i]==dice[i+1]){
                         full1++;
                     }
                    if(num2==dice[i] && dice[i]==dice[i+1]){
                         full2++;
                     }
                    }
                for(int i=0;i<5;i++){
                    house += dice[i];
                }
                if((full1==2 && full2==1) || (full1==1 && full2==2)){
                  return house;  
                }else{
                  return 0;  
                }
            case FOUR_OF_A_KIND:
                int king1=0;
                int king2=0;
                int resul1=0;
                int resul2=0;
                int uni1=0;
                int uni2=0;
                int contr=0;
                for(int i=0;i<4;i++){
                    if(i==0){
                      uni1=dice[i];  
                    }
                    if(dice[i]!=dice[i+1]&& contr==0){
                        uni2=dice[i+1];
                        contr=1;
                    }
                }
                for(int i=0;i<5;i++){
                    if(uni1==dice[i]){
                       king1++; 
                    }
                    if(uni2==dice[i]){
                       king2++; 
                    } 
                }
                if(king1>=4){
                   return uni1*4; 
                }else if(king2==4){
                   return uni2*4; 
                }else{
                    return 0;
                } 
            case LITTLE_STRAIGHT:
                  int Lstraight=0;
                for(int i=0;i<4;i++){
                    if((1+dice[i])==dice[i+1]){
                       Lstraight++; 
                    }
                    if(dice[4]==6){
                        break;
                    }
                }
                if(Lstraight==4){
                   return 30; 
                }else{
                   return 0; 
                }

                
            case BIG_STRAIGHT:
                int straight=0;
                for(int i=0;i<4;i++){
                    if((1+dice[i])==dice[i+1]){
                       straight++; 
                    }
                    if(dice[4]==5){
                        break;
                    }
                }
                if(straight==4){
                   return 30; 
                }else{
                   return 0; 
                }
            case CHOICE:
                int suma=0;
                for(int i=0;i<5;i++){
                    suma += dice[i];
                }
                return suma;
            default:
                return 0;
        }
        
    }

}
