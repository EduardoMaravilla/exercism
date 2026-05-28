public class Say {
    private String[] basicos={"zero","one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
    private String[] decena={"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
    private String[] miles={"hundred","thousand","million","billion"};

    public String say(long number) {
        if(number<0){
            throw new IllegalArgumentException("");
        }
        if(number>999_999_999_999L){
            throw new IllegalArgumentException("");
        }
        if(number<20){
            return basic(number);
        }
        if(number<=99){
            return desc(number);
        }
        if(number<=999){
            return centena(number);
        }
        if(number<=999999){
            return miles(number);
        }
        if(number<=999_999_999L){
            return millo(number);
        }else{
            return billones(number);
        }
        
    }
    private String basic(long number1){
               int b=(int)number1;
                return basicos[b];
    }
    private String desc(long number2){
               int d=(int)number2;
               int resta=0;
               int dd=0;
                for(int i=20;i<100;i=i+10){
                    resta=d-i;
                    if(resta>=0 && resta<=9){
                        break;
                    }
                    dd++;
                }
               if(resta==0){
                  return decena[dd]; 
               }else{
                   return decena[dd]+"-"+basicos[resta];
               }   
    }
    private String centena(long number3){
          long resta=0;
          int cc=1;
          for(long i=100;i<1000;i=i+100){
              resta=number3-i;
              if(resta>=0 && resta<=99){
                  break;
              }
              cc++;
          }
        if(resta==0){
            return basicos[cc]+" hundred";
        }else if(resta<20){
            return basicos[cc]+" hundred "+basic(resta);
        }else{
            return basicos[cc]+" hundred "+desc(resta);
        }
    }
    private String miles(long number4){
        long resta=0;
        long mm=1;
        for(long i=1000;i<999999;i=i+1000){
            resta=number4-i;
            if(resta>=0 && resta<=999){
               break; 
            }
            mm++;
        }
        String pri="";
        if(mm<20){
           pri=basic(mm); 
        }else if(mm<99) {
            pri=desc(mm);
        }else{
            pri=centena(mm);
        }
        if(resta==0){
            return pri+" thousand";
        }else{
            return pri+" thousand "+centena(resta);
        }
    }
     private String millo(long number5){
        long resta=0;
         long millon=1;
         for(long i=1_000_000L;i<999_999_999L;i=i+1_000_000L){
             resta=number5-i;
             if(resta>=0 && resta<=999999){
               break; 
              }
             millon++;
         }
         String pri="";
         if(millon<20){
             pri=basic(millon);
         }else if(millon<99){
             pri=desc(millon);
         }else if(millon<999){
             pri=centena(millon);
         }
         if(resta==0){
            return pri+" million";
        }else if(resta<20){
            return pri+" million "+basic(resta);
        }else if(resta<99){
            return pri+" million "+desc(resta);
        }else if(resta<999){
            return pri+" million "+centena(resta);
        }else {
            return pri+" million "+miles(resta);
        } 
    }
    private String billones(long number6){
        long resta=0;
         long billon=1;
        for(long i=1_000_000_000L;i<999_999_999_999L;i=i+1_000_000_000L){
             resta=number6-i;
             if(resta>=0 && resta<=999_999_999L){
               break; 
              }
             billon++;
         }
        String pri="";
         if(billon<20){
             pri=basic(billon);
         }else if(billon<99){
             pri=desc(billon);
         }else if(billon<999){
             pri=centena(billon);
         }
        if(resta==0){
            return pri+" billion";
        }else{
            return pri+" billion "+millo(resta);
        }
    } 
}
//esto es una prueba