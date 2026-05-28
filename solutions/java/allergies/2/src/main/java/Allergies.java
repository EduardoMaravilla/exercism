import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

class Allergies{
    private final List<Allergen> alergias;

    Allergies(int num){
        alergias = new ArrayList<>();
        while (num > 256){
            num -= 256;
        }        
        int control= num;
        while(control>=128){
            alergias.add(Allergen.CATS);
            control -= 128;
        }
        while(control>=64){
            alergias.add(Allergen.POLLEN);
            control -= 64;
        }
        while(control>=32){
            alergias.add(Allergen.CHOCOLATE);
            control -= 32;
        }
        while(control>=16){
            alergias.add(Allergen.TOMATOES);
            control -= 16;
        }
        while(control>=8){
            alergias.add(Allergen.STRAWBERRIES);
            control -= 8;
        }
        while(control>=4){
            alergias.add(Allergen.SHELLFISH);
            control -= 4;
        }
        while(control>=2){
            alergias.add(Allergen.PEANUTS);
            control -= 2;
        }
        while(control == 1){
            alergias.add(Allergen.EGGS);
            control -= 1;
        }
    }
    boolean isAllergicTo(Allergen alergia){
        return alergias.contains(alergia);
    }
    List<Allergen> getList(){
        List<Allergen> lista = alergias.stream().distinct().collect(Collectors.toList());
        Collections.reverse(lista);
        return lista;
    }
}