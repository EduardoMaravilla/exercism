import java.util.List;
import java.util.ArrayList;


class VariableLengthQuantity {

    List<String> encode(List<Long> numbers) {
       List<String> codeList=new ArrayList<>();
       for (Long numLong : numbers) {
        String binarieString=Long.toBinaryString(numLong);
        int binaryLength=binarieString.length();
        double num=binaryLength/7.0;
        int valor=(int) Math.ceil(num);
        String[] binarios=new String[valor];
        
        if(binaryLength>7){
            int control1=0;
            String cadena="";
            int control2=0;
            for(int i=(binaryLength-1);i>=0;i--){
                cadena=binarieString.substring(i, i+1)+cadena;
                control1++;
                if(control1==7 || i==0){
                  if(control2>0){
                    if(cadena.length()<7){
                       for (int j = 0; j <(7-control1); j++) {
                         cadena="0"+cadena;
                       }
                       binarios[control2]="1"+cadena;
                        control2++;
                        control1=0;
                        cadena="";

                    }else{
                        binarios[control2]="1"+cadena;
                        control2++;
                        control1=0;
                        cadena="";
                    }
                  }else{
                    binarios[control2]="0"+cadena;
                    control1=0;
                    control2++;
                    cadena="";
                  }
                }             
            }
            int tam=codeList.size();
            for (String cad : binarios) {
                Long binary=Long.parseLong(cad,2);
                String nuevoString=Long.toHexString(binary);
                codeList.add(tam,"0x"+nuevoString);
            }
        }else{
          Long valor1=Long.parseLong(binarieString,2);
          String newcoding=Long.toHexString(valor1);
          codeList.add("0x"+newcoding);   
        }
       }
       return codeList;
    }

    List<String> decode(List<Long> bytes) {
        List<String> encodeList=new ArrayList<>();
        int num=bytes.size();
        String cadena="";
        for (Long number : bytes) {
            String binaryString=Long.toBinaryString(number);
            int num3=binaryString.length();
            if(num3<8){
                for (int i = 0; i <(8-num3); i++) {
                    binaryString="0"+binaryString;
                }

            }
            
            if(num==1 && binaryString.substring(0, 1).equals("1") && binaryString.length()==8){
               throw new IllegalArgumentException("Invalid variable-length quantity encoding");  
            }
            
            if(num>1){
                
                if(binaryString.substring(0, 1).equals("1")){
                   cadena=cadena+binaryString.substring(1);
                }else if(binaryString.substring(0, 1).equals("0")){                  
                    cadena=cadena+binaryString.substring(1);
                    Long val1=Long.parseLong(cadena,2);
                    String val2=Long.toHexString(val1);
                    encodeList.add("0x"+val2);
                    cadena="";
                }

            }else{            
                Long valor1=Long.parseLong(binaryString,2);
                String valor2=Long.toHexString(valor1);
                encodeList.add("0x"+valor2);
            }
            
        }     
        return encodeList;
    }
}
