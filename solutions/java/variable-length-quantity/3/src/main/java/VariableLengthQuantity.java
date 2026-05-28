import java.util.ArrayList;
import java.util.List;
class VariableLengthQuantity {
    List<String> encode(List<Long> numbers) {
        List<String> encode=new ArrayList<>();
        for (Long number: numbers){
             List<String> temp=new ArrayList<>();
             temp.add(String.format("0x%x", number & 0x7f));
             number >>= 7;
            while(number >0){
                temp.add(String.format("0x%x", number & 0x7f | 0X80));
                number >>= 7;
            }
            for (int i = temp.size() - 1; i >=0 ; i--) {
                encode.add(temp.get(i));
            }
        }
        return encode;
    }
    List<String> decode(List<Long> bytes) {
        if ((bytes.get(bytes.size()-1) & 0x80L) != 0){
            throw new IllegalArgumentException("Invalid variable-length quantity encoding");
        }
        List<String> decode = new ArrayList<>();
        long number= 0L;
        for(Long byt: bytes){
            number <<= 7;
            number += byt & 0x7f;
            if((byt & 0x80) == 0){
                decode.add(String.format("0x%x",number));
                number = 0L;
            }
        }
        return decode;
    }
}