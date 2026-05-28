import java.util.Random;
import java.util.Set;
import java.util.HashSet;

class Robot {
    private static final Set<String> usedNames = new HashSet<>();
    private String name;

    public Robot() {
        this.name = generateUniqueName();
    }

    public String getName() {
        return name;
    }
    public void reset() {
        this.name = generateUniqueName();
    }

    private String generateUniqueName() {
        String newName;
        do {
            newName = generateRandomName();
        } while (usedNames.contains(newName));
        usedNames.add(newName);
        return newName;
    }

    private String generateRandomName() {
        Random random = new Random();
        char letter1 = (char)(random.nextInt(26) + 'A');
        char letter2 = (char)(random.nextInt(26) + 'A');
        int num = random.nextInt(1000);
        return String.format("%c%c%03d", letter1, letter2, num);
    }

    public static Set<String> getUsedNames() {
        return new HashSet<>(usedNames);
    }
}