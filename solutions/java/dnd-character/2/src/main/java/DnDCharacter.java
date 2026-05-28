import java.util.List;
import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

class DnDCharacter {
    private final Random random = new Random();
    private Integer Strength = null;
    private Integer Dexterity = null;
    private Integer Constitution = null;
    private Integer Intelligence = null;
    private Integer Wisdom = null;
    private Integer Charisma = null;

    int ability(List<Integer> scores) {
        int suma = 0;
        Integer[] num = scores.toArray(new Integer[0]);
        Arrays.sort(num, Comparator.reverseOrder());
        for (int j = 0; j <= 2; j++) {
            suma += num[j];
        }
        return suma;
    }

    List<Integer> rollDice() {
        List<Integer> tiros = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
           tiros.add(random.nextInt(6)+1 );
        }
        return tiros;
    }

    int modifier(int input) {        
        return (int) Math.floor((input - 10.0) / 2.0);
    }

    int getStrength() {
        if (Strength == null) {
            Strength = ability(rollDice());
        }
        return Strength;
    }

    int getDexterity() {
        if (Dexterity == null) {
            Dexterity = ability(rollDice());
        }
        return Dexterity;
    }

    int getConstitution() {
        if (Constitution == null) {
            Constitution = ability(rollDice());
        }
        return Constitution;
    }

    int getIntelligence() {
        if (Intelligence == null) {
            Intelligence = ability(rollDice());
        }
        return Intelligence;
    }

    int getWisdom() {
        if (Wisdom == null) {
            Wisdom = ability(rollDice());
        }
        return Wisdom;
    }

    int getCharisma() {
        if (Charisma == null) {
            Charisma = ability(rollDice());
        }
        return Charisma;
    }

    int getHitpoints() {
        return 10 + modifier(getConstitution());
    }
}

