import java.util.ArrayList;
import java.util.List;

public class IntergalacticTransmission {

    public static List<Integer> getTransmitSequence(List<Integer> message) {
        List<Boolean> bitStream = new ArrayList<>();

        for (int value : message) {
            for (int i = 7; i >= 0; i--) {
                bitStream.add(((value >> i) & 1) == 1);
            }
        }
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < bitStream.size(); i += 7) {
            int byteWithParity = getByteWithParity(i, bitStream);
            result.add(byteWithParity);
        }
        return result;
    }

    private static int getByteWithParity(int i, List<Boolean> bitStream) {
        int byteWithParity = 0;
        int bitCount = 0;

        for (int j = 0; j < 7; j++) {
            int bitIndex = i + j;
            boolean bit = (bitIndex < bitStream.size()) ? bitStream.get(bitIndex) : false; // rellenar con 0 si falta
            byteWithParity = (byteWithParity << 1) | (bit ? 1 : 0);
            if (bit) bitCount++;
        }

        int parityBit = (bitCount % 2 == 0) ? 0 : 1;
        byteWithParity = (byteWithParity << 1) | parityBit;
        return byteWithParity;
    }


    public static List<Integer> decodeSequence(List<Integer> sequence) {
        List<Boolean> dataBits = new ArrayList<>();

        for (int value : sequence) {
            int parityBit = value & 1;
            int data = value >>> 1;
            int countOnes = Integer.bitCount(data) + parityBit;

            if (countOnes % 2 != 0) {
                throw new IllegalArgumentException();
            }
            
            for (int i = 6; i >= 0; i--) {
                dataBits.add(((data >> i) & 1) == 1);
            }
        }
        
        List<Integer> originalBytes = new ArrayList<>();
        for (int i = 0; i + 7 < dataBits.size(); i += 8) {
            int value = 0;
            for (int j = 0; j < 8; j++) {
                value = (value << 1) | (dataBits.get(i + j) ? 1 : 0);
            }
            originalBytes.add(value);
        }

        return originalBytes;
    }
    
}