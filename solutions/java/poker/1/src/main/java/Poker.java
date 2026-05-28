import java.util.Collections;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Poker {
    private List<String> hands;

    public Poker(List<String> hands) {
        this.hands = hands;
    }

    public List<String> getBestHands() {
        if(hands.size()<=1){
          return hands;   
        }
        
        List<playofcard> cardList=new ArrayList<>();
         for (int i = 0; i < hands.size(); i++) {
             cardList.add(new playofcard(hands.get(i), handvalue(hands.get(i))));
         }
         Comparator<playofcard> byvalue = Comparator.comparingInt(playofcard::getvalue);
         Collections.sort(cardList, byvalue);
        int control=0;
         for (int i = 0; i < (cardList.size()-1); i++) {
            if(cardList.get(i).getvalue()==cardList.get(i+1).getvalue()){
               control++; 
            }else{
               break;
            }
            if(cardList.size()==2){
               break;
            }
         }    
         if(control==0){
            playofcard Mano=cardList.get(0);
            String ganador=Mano.getplay();
            return Collections.singletonList(ganador);
         }else{

             List<playofcard> ncardList=new ArrayList<>();
          for (int i = 0; i <= control; i++) {
            ncardList.add(cardList.get(i));
          }
             List<String> bestcard=analizar(ncardList);
             
             return bestcard;
         }     
    }
    private static List<String> analizar(List<playofcard> lista){
      List<String> respuesta=new ArrayList<>();
      List<String> temporal=new ArrayList<>();
      int control=lista.get(0).getvalue();
      System.out.println(control);
      for (int i = 0; i < lista.size(); i++) {
           temporal.add(recode(lista.get(i).getplay()));
           respuesta.add(lista.get(i).getplay());
      }
      for (String string : temporal) {
         System.out.println(string);
      }
      if(control==9||control==4){
          for (int i = 0; i < (temporal.size()-1); i++) {
              for (int j = 12; j >=0; j=j-3) {
                  String val1=temporal.get(i).substring(j, j+1);
                  String val2=temporal.get(i+1).substring(j, j+1);
                  if(val1.compareTo(val2)<0){
                      respuesta.remove(0);
                      break;
                  }else if(val2.compareTo(val1)<0){
                    respuesta.remove(1);
                    break;
                  }                
              }
          }
        }else if(control==2||control==3){
          int cont0=0;
          int cont1=0;
          for (int i = 0; i < (temporal.size()-1); i++) {
            for (int j = 12; j >=0; j=j-3) {
                String val1=temporal.get(i).substring(j, j+1);
                String val2=temporal.get(i+1).substring(j, j+1);
                if(val1.compareTo(val2)<0){
                    cont0++;
                }else if(val2.compareTo(val1)<0){
                  cont1++;
                }                
            }
            if(cont0<cont1){
                respuesta.remove(1);
            }else if(cont1<cont0){
              respuesta.remove(0);
            }
            cont0=0;
            cont1=0;
        
          }     
      }else if(control==1||control==5){
        
        for (int i = 0; i < temporal.size(); i++) {
             String hayA=temporal.get(i).substring(12, 13);
             String hay2=temporal.get(i).substring(0, 1);
             if(hayA.equals("O") && hay2.equals("C")){
              String valor=temporal.get(i);
              valor=valor.replaceAll("O", "B");
              temporal.set(i, valor);
             }
        }
          for (int i = 0; i < (temporal.size()-1); i++) {
            for (int j = 12; j >=0; j=j-3) {
                String val1=temporal.get(i).substring(j, j+1);
                String val2=temporal.get(i+1).substring(j, j+1);
                if(val1.compareTo(val2)<0){
                    respuesta.remove(0);
                    break;
                }else if(val2.compareTo(val1)<0){
                  respuesta.remove(1);
                  break;
                }                
            }
        }
      }else if(control==8||control==7||control==6){
        List<String> Ntemporal=new ArrayList<>();
        String char6="";
        String char1="";
        String char2="";
        String char3="";
        String char4="";
        int num0=0;
        int num1=0;
        int num2=0;
        int num3=0;
        int num4=0;


        for (int i = 0; i < temporal.size(); i++) {
            String cad=temporal.get(i);
            for (int j = 0; j < cad.length(); j=j+3) {
              char6=cad.substring(j, j+1);
              if(j==0){
                char1=char6;
                num1++;
              }else if(!char1.equals(char6)&& num0==0){
                 char2=char6;
                 num2++;
                 num0++;
              }else if(!char1.equals(char6) && !char2.equals(char6)&& num0==1){
                char3=char6;
                 num3++;
                 num0++;
              }else if(!char1.equals(char6) && !char2.equals(char6)&& !char3.equals(char6)&&num0==2){
                char4=char6;
                 num4++;
                 num0++;
              }else if(char1.equals(char6)){
                 num1++;
              }else if(char2.equals(char6)){
                num2++;
             }else if(char3.equals(char6)){
              num3++;
             }else if(char4.equals(char6)){
              num4++;
             }                
            }
            if(num1==3|| num2==3 || num3==3){
              System.out.println("Caso Trio");
              if(num1==3){
                if(char2.compareTo(char3)<0){
                   Ntemporal.add(char1+char3+char2);
                }else{
                   Ntemporal.add(char1+char2+char3);
                }
              }else if(num2==3){
                if(char1.compareTo(char3)<0){
                  Ntemporal.add(char2+char3+char1);
               }else{
                  Ntemporal.add(char2+char1+char3);
               }
              }else if(num3==3){
                if(char1.compareTo(char2)<0){
                  Ntemporal.add(char3+char2+char1);
               }else{
                  Ntemporal.add(char3+char1+char2);
               }
              }
            }else if(num1==2 && num2==2||num1==2 && num3==2 || num2==2 && num3==2){
                 if(num1==2 && num2==2){
                  if(char1.compareTo(char2)<0){
                    Ntemporal.add(char2+char1+char3);
                  }else{
                    Ntemporal.add(char1+char2+char3);
                  }
                 }else if(num1==2 && num3==2){
                  if(char1.compareTo(char3)<0){
                    Ntemporal.add(char3+char1+char2);
                  }else{
                    Ntemporal.add(char1+char3+char2);
                  }
                 }
                 else if(num2==2 && num3==2){
                  if(char2.compareTo(char3)<0){
                    Ntemporal.add(char3+char2+char1);
                  }else{
                    Ntemporal.add(char2+char3+char1);
                  }
                 }
            }else{
                 String[] vec1={char2,char3,char4};
                 Arrays.sort(vec1, 0, 3, null);
                 String[] vec2={char1,char3,char4};
                 Arrays.sort(vec2, 0, 3, null);
                 String[] vec3={char1,char2,char4};
                 Arrays.sort(vec3, 0, 3, null);
                 String[] vec4={char1,char2,char3};
                 Arrays.sort(vec4, 0, 3, null);
                if(num1==2){
                   Ntemporal.add(char1+String.join("", vec1));
                }else if(num2==2){
                  Ntemporal.add(char2+String.join("", vec2));
                }else if(num3==2){
                  Ntemporal.add(char3+String.join("", vec3));
                }else if(num4==2){
                  Ntemporal.add(char4+String.join("", vec4));
                }
                
            }
           char6="";
           char1="";
           char2="";
           char3="";
           char4="";
           num0=0;
           num1=0;
           num2=0;
           num3=0;
           num4=0;
        }
        for (String string : Ntemporal) {
          System.out.println(string);
        }

        for (int i = 0; i < (Ntemporal.size()-1); i++) {
            for (int j = 0; j < Ntemporal.get(0).length(); j++) {
              String val1=Ntemporal.get(i).substring(j, j+1);
              String val2=Ntemporal.get(i+1).substring(j, j+1);
              if(val1.compareTo(val2)<0){
                respuesta.remove(0);
                break;
            }else if(val2.compareTo(val1)<0){
              respuesta.remove(1);
              break;
            } 
              
            }         
        }
      } 
      return respuesta;
    }
    
   private static class playofcard {
      private String juego;
      private int point;
  
      public playofcard(String juego, int point) {
          this.juego = juego;
          this.point = point;
      }
  
      public String getplay() {
          return juego;
      }
  
      public int getvalue() {
          return point;
      }
  }
    private static int handvalue(String hand){
         int valor=typehand(hand).ordinal();
         return valor;  
    }

    private static boolean sameAll(String hand){
        //String[] tipe={"S","H","D","C"};
        String[] val1=hand.split(" ");        
        String val2=val1[0].substring(1,2);   
        int cont=0;
        for(int i=0; i<val1.length;i++){
                if(val1[i].substring(1,2).equals(val2)){
               cont++; 
                    }
        }
        if(cont==5){
          return true;  
        }else{
            return false;
        }  
    }
    private static String recode(String hand){
      String card=hand;
             card=card.replaceAll("S", "W");
             card=card.replaceAll("H", "X");
             card=card.replaceAll("D", "Y");
             card=card.replaceAll("C", "Z");
             card=card.replaceAll("J", "L");
             card=card.replaceAll("Q", "M");
             card=card.replaceAll("K", "N");
             card=card.replaceAll("A", "O");
             card=card.replaceAll("10", "K");
             card=card.replaceAll("2", "C");
             card=card.replaceAll("3", "D");
             card=card.replaceAll("4", "E");
             card=card.replaceAll("5", "F");
             card=card.replaceAll("6", "G");
             card=card.replaceAll("7", "H");
             card=card.replaceAll("8", "I");
             card=card.replaceAll("9", "J");
             String[] cards=card.split(" ");
             Arrays.sort(cards, 0, 5, null);
              card=String.join(" ", cards);
      return card;

    }
    private static handCategory typehand(String hand){
            String card=recode(hand);
            String[] cards=card.split(" ");
            Arrays.sort(cards, 0, 5, null);
             handCategory type=handCategory.none;

             String cadena="";
                for (int i = 0; i < cards.length; i++) {
                    cadena += cards[i].substring(0, 1);
                }
                int suma=0;
                for (int j = 0; j < (cards.length-1); j++) {
                    char c=cards[j].charAt(0);
                    char d=cards[j+1].charAt(0);
                    c=(char)(c+1);
                    if(c==d){
                      suma++;
                    }
                }
             if(sameAll(card)){
                //Flush
                type=handCategory.flush;
                //Royal_Flush
                if(cadena.equals("KLMNO")){
                     type=handCategory.royal_flush;
                     return type;
                }
                //Straight Flush
                if(cadena.equals("CDEFO")){
                    type=handCategory.straight_flush;
                    return type;
                }
                if(suma==4){
                   type=handCategory.straight_flush;
                   return type;
                }else{
                   return type; 
                }
             }else{
                

                 String[] cartas1=new String[cards.length];

                 for (int i = 0; i < cartas1.length; i++) {
                    cartas1[i]=cards[i].substring(0, 1);
                 }
                 
                 int control=0;
                 String cad1="";
                 String cad2="";
                 String cad3="";
                 String cad4="";
                 String cad5="";
                 
                 for (int i = 0; i < cartas1.length; i++) {
                    if(i==0){
                      cad1=cartas1[i];
                    }else if(!cartas1[i-1].equals(cartas1[i]) && control==0){
                      cad2=cartas1[i];
                      control++;
                    }else if(!cartas1[i-1].equals(cartas1[i]) && !cad1.equals(cartas1[i]) && control==1){
                        cad3=cartas1[i];
                        control++;
                    }else if(!cartas1[i-1].equals(cartas1[i]) && !cad1.equals(cartas1[i]) && !cad2.equals(cartas1[i]) && control==2){
                        cad4=cartas1[i];
                        control++;
                    }else if(!cartas1[i-1].equals(cartas1[i]) && !cad1.equals(cartas1[i]) && !cad2.equals(cartas1[i]) && !cad3.equals(cartas1[i])&& control==3){
                        cad5=cartas1[i];
                        control++;
                    }
                 }
                 int con1=0;
                 int con2=0;
                 int con3=0;
                 int con4=0;
                 int con5=0;
                  for (int i = 0; i < cartas1.length; i++) {
                    if(cad1.equals(cartas1[i])){
                        con1++;
                    }else if(cad2.equals(cartas1[i])){
                        con2++;
                    }else if(cad3.equals(cartas1[i])){
                        con3++;
                    }else if(cad4.equals(cartas1[i])){
                        con4++;
                    }else if(cad5.equals(cartas1[i])){
                        con5++;
                    }
                  }
                  // four_of_a_kind cards
                  if(con1==4 || con2==4 ){
                      type=handCategory.four_of_a_kind;
                      return type;
                  }
                  // FULL HOUSE
                  if(con1==3 && con2==2 || con1==2 && con2==3){ 
                       type=handCategory.full_house;
                       return type;
                  }
                  //Straight
                  if(cadena.equals("CDEFO")){
                    type=handCategory.straight; 
                    return type;
                  }else if(suma==4){
                    type=handCategory.straight;
                    return type;
                 }
                 //three of a kind
                 if(con1==3 || con2==3 || con3==3){
                    type=handCategory.three_of_a_kind;
                    return type;
                 }
                 //Two pair
                 if((con1==2 && con2==2) || (con1==2 && con3==2) || (con2==2 && con3==2)){
                    type=handCategory.two_pairs;
                    return type;
                 }
                 //One pair
                 if(con1==2 || con2==2 || con3==2 || con4==2){
                    type=handCategory.one_pair;
                    return type;
                 }
                 if(con1==1 && con2==1 && con3==1 && con4==1 && con5==1){
                    type=handCategory.high_card;
                    return type;
                 }


                return handCategory.none;
             }
    }
    private static enum handCategory {

        royal_flush,
        straight_flush,
        four_of_a_kind,
        full_house,
        flush,
        straight,
        three_of_a_kind,
        two_pairs,
        one_pair,
        high_card,
        none
    }
}
