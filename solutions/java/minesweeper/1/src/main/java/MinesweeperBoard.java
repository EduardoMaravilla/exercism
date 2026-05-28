import java.util.List;
import java.util.ArrayList;


class MinesweeperBoard{
     private List<String> minas=new ArrayList<>();
    
    public MinesweeperBoard(List<String> mine){
        minas=mine;
    }
    List<String> withNumbers(){
        List<String> resultado=new ArrayList<>();
        String cadena1="";
        String cadena2="";
        String cadena3="";
         if(minas.isEmpty()){
             return minas;
         }
        if(minas.size()==1 && minas.get(0).length()>1){
            String[] cade4=minas.get(0).split("");            
            for(int j=0;j<minas.get(0).length();j++){
                int min=0;                
                if(j==0){
                    if(cade4[j].contains(" ")){
                        if(cade4[j+1].contains("*")){
                            min++;
                        }
                    }
                }else if(j==(minas.get(0).length()-1)){
                     if(cade4[j].contains(" ")){
                        if(cade4[j-1].contains("*")){
                            min++;
                        }
                    }
                }else{
                    if(cade4[j].contains(" ")){
                        if(cade4[j-1].contains("*")){
                            min++;
                        }
                        if(cade4[j+1].contains("*")){
                            min++;
                        }
                }
            }
                if(min>0){
                   cade4[j]=Integer.toString(min); 
                }
          }
            String cadeUnida = String.join("",cade4);
            resultado.add(cadeUnida);
            return resultado;
        }else if(minas.size()>1 && minas.get(0).length()==1){
                        
            for(int j=0;j<minas.size();j++){
                int min=0;                
                if(j==0){
                    if(minas.get(j).contains(" ")){
                        if(minas.get(j+1).contains("*")){
                            min++;
                        }
                    }
                }else if(j==(minas.size()-1)){
                     if(minas.get(j).contains(" ")){
                        if(minas.get(j-1).contains("*")){
                            min++;
                        }
                    }
                }else{
                    if(minas.get(j).contains(" ")){
                        if(minas.get(j-1).contains("*")){
                            min++;
                        }
                        if(minas.get(j+1).contains("*")){
                            min++;
                        }
                }
            }
                if(min>0){
                   resultado.add(Integer.toString(min)); 
                }else{
                    resultado.add(minas.get(j));
                }
          }  
            return resultado;
        }

        if(minas.size()>1 && minas.get(0).length()>1){
            for(int i=0; i<minas.size();i++){
            if(i==0){
              cadena1=minas.get(i);
              cadena2=minas.get(i+1);
            }else if(i==(minas.size()-1)){
               cadena1=minas.get(i);
               cadena2=minas.get(i-1); 
            }else{
                cadena1=minas.get(i);
                cadena2=minas.get(i-1);
                cadena3=minas.get(i+1);
            }
            String[] cade1=cadena1.split("");
            String[] cade2=cadena2.split("");
            String[] cade3=cadena3.split("");
            for(int j=0;j<minas.get(0).length();j++){
                int num=0;
                if(i==0){
                    if(j==0){
                        if(cade1[j].contains(" ")){
                            if(cade1[j+1].contains("*")){
                                num++;
                            }
                            if(cade2[j].contains("*")){
                                num++;
                            }
                            if(cade2[j+1].contains("*")){
                                num++;
                            }
                        }
                    }else if(j==(minas.get(0).length()-1)){
                        if(cade1[j].contains(" ")){
                            if(cade1[j-1].contains("*")){
                                num++;
                            }
                            if(cade2[j].contains("*")){
                                num++;
                            }
                            if(cade2[j-1].contains("*")){
                                num++;
                            }
                        }
                    }else{
                        if(cade1[j].contains(" ")){
                            if(cade1[j+1].contains("*")){
                                num++;
                            }
                            if(cade1[j-1].contains("*")){
                                num++;
                            }
                            if(cade2[j].contains("*")){
                                num++;
                            }
                            if(cade2[j+1].contains("*")){
                                num++;
                            }
                            if(cade2[j-1].contains("*")){
                                num++;
                            }
                        }
                        
                    }
                    if(num>0){
                        cade1[j]=Integer.toString(num);
                    }
                    
                    
                }else if(i==(minas.size()-1)){
                     if(j==0){
                        if(cade1[j].contains(" ")){
                            if(cade1[j+1].contains("*")){
                                num++;
                            }
                            if(cade2[j].contains("*")){
                                num++;
                            }
                            if(cade2[j+1].contains("*")){
                                num++;
                            }
                        }
                    }else if(j==(minas.get(0).length()-1)){
                        if(cade1[j].contains(" ")){
                            if(cade1[j-1].contains("*")){
                                num++;
                            }
                            if(cade2[j].contains("*")){
                                num++;
                            }
                            if(cade2[j-1].contains("*")){
                                num++;
                            }
                        }
                    }else{
                        if(cade1[j].contains(" ")){
                            if(cade1[j+1].contains("*")){
                                num++;
                            }
                            if(cade1[j-1].contains("*")){
                                num++;
                            }
                            if(cade2[j].contains("*")){
                                num++;
                            }
                            if(cade2[j+1].contains("*")){
                                num++;
                            }
                            if(cade2[j-1].contains("*")){
                                num++;
                            }
                        }
                        
                    }
                    if(num>0){
                        cade1[j]=Integer.toString(num);
                    }
                }else{
                    if(j==0){
                        if(cade1[j].contains(" ")){
                           if(cade1[j+1].contains("*")){
                               num++;
                           }
                           if(cade2[j].contains("*")){
                               num++;
                           }
                           if(cade2[j+1].contains("*")){
                               num++;
                           }
                           if(cade3[j].contains("*")){
                               num++;
                           }
                           if(cade3[j+1].contains("*")){
                               num++;
                           }  
                        }
                    }else if(j==(minas.get(0).length()-1)){
                        if(cade1[j].contains(" ")){
                           if(cade1[j-1].contains("*")){
                               num++;
                           }
                           if(cade2[j].contains("*")){
                               num++;
                           }
                           if(cade2[j-1].contains("*")){
                               num++;
                           }
                           if(cade3[j].contains("*")){
                               num++;
                           }
                           if(cade3[j-1].contains("*")){
                               num++;
                           }  
                        }
                        
                    }else{
                        if(cade1[j].contains(" ")){
                           if(cade1[j-1].contains("*")){
                               num++;
                           }
                           if(cade1[j+1].contains("*")){
                               num++;
                           }
                           if(cade2[j].contains("*")){
                               num++;
                           }
                           if(cade2[j-1].contains("*")){
                               num++;
                           }
                            if(cade2[j+1].contains("*")){
                               num++;
                           }
                           if(cade3[j].contains("*")){
                               num++;
                           }
                           if(cade3[j-1].contains("*")){
                               num++;
                           }
                            if(cade3[j+1].contains("*")){
                               num++;
                           }
                        }
                    }
                    if(num>0){
                        cade1[j]=Integer.toString(num);
                    }
                }
            }
            String cadenaUnida = String.join("", cade1);
            resultado.add(cadenaUnida);
        }
        
        return resultado;
            
        }
        return minas;
    }
}