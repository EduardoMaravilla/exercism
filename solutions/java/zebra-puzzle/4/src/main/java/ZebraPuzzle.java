import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ZebraPuzzle {
    private final List<House> houses = new ArrayList<>(5);

    private enum Nationality { ENGLISH, SPANIARD, UKRAINIAN, NORWEGIAN, JAPANESE}
    private enum Color       { RED, GREEN, IVORY, YELLOW, BLUE }
    private enum Brand       { OLD_GOLD, KOOLS, CHESTERFIELDS, LUCKY_STRIKE, PARLIAMENTS }
    private enum Pet         { DOG, SNAILS, FOX, HORSE, ZEBRA }
    private enum Beverage    { TEA, MILK, ORANGE_JUICE, COFFEE, WATER }

    record House(Nationality nationality, Color color, Brand brand, Pet pet, Beverage beverage){
        @Override
        public String toString() {
            return String.format("N[%s] C[%s] B[%s] P[%s] BV[%s]",nationality, color, brand, pet, beverage);
        }
    }

    public ZebraPuzzle() {
        // call .values() once to prevent new array creation with in the iterations
        Nationality[] nationalitiesArray = Nationality.values();
        Color[] colorsArray = Color.values();
        Brand[] brandsArray = Brand.values();
        Pet[] petsArray = Pet.values();
        Beverage[] beveragesArray = Beverage.values();

        finish:
        for (List<Nationality> nationalities : getPermutations(nationalitiesArray)) {
            // The Norwegian lives in the first house.
            if (nationalities.indexOf(Nationality.NORWEGIAN) != 0) {
                continue;
            }

            for (List<Pet> pets : getPermutations(petsArray)) {
                // The Spaniard owns the dog.
                if (pets.indexOf(Pet.DOG) != nationalities.indexOf(Nationality.SPANIARD)) {
                    continue;
                }

                for (List<Color> colors : getPermutations(colorsArray)) {
                    if (
                            // The Norwegian lives next to the blue house.
                            colors.get(1) != Color.BLUE ||
                            // The Englishman lives in the red house.
                            (colors.indexOf(Color.RED) != nationalities.indexOf(Nationality.ENGLISH)) ||
                            // The green house is immediately to the right of the ivory house.
                            (colors.indexOf(Color.GREEN) - colors.indexOf(Color.IVORY) != 1)
                    ) {
                        continue;
                    }

                    for (List<Beverage> beverages : getPermutations(beveragesArray)) {
                        if (
                                // Milk is drunk in the middle house.
                                beverages.get(2) != Beverage.MILK ||
                                // Coffee is drunk in the green house.
                                (beverages.indexOf(Beverage.COFFEE) != colors.indexOf(Color.GREEN)) ||
                                // The Ukrainian drinks tea.
                                (nationalities.indexOf(Nationality.UKRAINIAN) != beverages.indexOf(Beverage.TEA))
                        ) {
                            continue;
                        }

                        for (List<Brand> cigarettes : getPermutations(brandsArray)) {
                            if (
                                    // The Old Gold smoker owns snails.
                                    (cigarettes.indexOf(Brand.OLD_GOLD) != pets.indexOf(Pet.SNAILS)) ||
                                    // Kools are smoked in the yellow house.
                                    (cigarettes.indexOf(Brand.KOOLS) != colors.indexOf(Color.YELLOW)) ||
                                    // The Lucky Strike smoker drinks orange juice.
                                    (cigarettes.indexOf(Brand.LUCKY_STRIKE) != beverages.indexOf(Beverage.ORANGE_JUICE)) ||
                                    // The Japanese smokes Parliaments
                                    (cigarettes.indexOf(Brand.PARLIAMENTS) != nationalities.indexOf(Nationality.JAPANESE)) ||
                                    // The man who smokes Chesterfields lives in the house next to the man with the fox
                                    (Math.abs(cigarettes.indexOf(Brand.CHESTERFIELDS) - pets.indexOf(Pet.FOX)) != 1) ||
                                    // Kools are smoked in the house next to the house where the horse is kept.
                                    (Math.abs(cigarettes.indexOf(Brand.KOOLS) - pets.indexOf(Pet.HORSE)) != 1)
                            ) {
                                continue;
                            }

                            // create houses array
                            for (int i = 0; i < 5; i++) {
                                houses.add(new House(nationalities.get(i), colors.get(i), cigarettes.get(i) ,
                                        pets.get(i), beverages.get(i)));
                            }
                            break finish;
                        }
                    }
                }
            }
        }
        if (houses.isEmpty()) {
            throw new IllegalArgumentException("No solution found");
        }
    }

    private static <T> List<List<T>> getPermutations(T[] array) {
        List<List<T>> permutations = new ArrayList<>();
        permute(permutations, array, 0);
        return permutations;
    }

    private static <T> void permute(List<List<T>> permutations, T[] array, int startIndex) {
        if (startIndex == (array.length - 1)) {
            permutations.add(new ArrayList<>(Arrays.asList(array)));
        }
        for (int i = startIndex; i < array.length; i++) {
            T tmp = array[startIndex];
            array[startIndex] = array[i];
            array[i] = tmp;

            permute(permutations, array, startIndex + 1);

            tmp = array[startIndex];
            array[startIndex] = array[i];
            array[i] = tmp;
        }
    }

    String getWaterDrinker() {
//        System.out.println(houses.stream().map(House::toString).collect(Collectors.joining("\n")));
        String result = houses.stream()
                .filter(h -> h.beverage == Beverage.WATER).findFirst()
                .get()
                .nationality
                .toString();
        return result.substring(0, 1).toUpperCase() + result.substring(1).toLowerCase();
    }

    String getZebraOwner() {
        String result = houses.stream()
                .filter(h -> h.pet == Pet.ZEBRA)
                .findFirst()
                .get()
                .nationality
                .toString();
        return result.substring(0, 1).toUpperCase() + result.substring(1).toLowerCase();
    }
}