import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Allergies {
    private final List<Allergen> allergens;
    Allergies(int num) {
        allergens = new ArrayList<>();
        for (Allergen allergen : Allergen.values()) {
            if ((num & allergen.getScore()) != 0) {
                allergens.add(0, allergen); 
                num -= allergen.getScore();
            }
        }
    }
    boolean isAllergicTo(Allergen allergen) {
        return allergens.contains(allergen);
    }
    List<Allergen> getList() {
        List<Allergen> listofAllergens = new ArrayList<>(allergens);
        Collections.reverse(listofAllergens);
        return listofAllergens;
    }
}