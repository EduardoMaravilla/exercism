import java.util.*;

public class KindergartenGarden {
    private List<Character> rowone = new ArrayList<>();
    private List<Character> rowtwo = new ArrayList<>();
    private final String[] listStudents = {"Alice", "Bob", "Charlie", "David",
            "Eve", "Fred", "Ginny", "Harriet",
            "Ileana", "Joseph", "Kincaid", "Larry"};

    public KindergartenGarden(String garden) {
        String[] rows = garden.split("\n");
        for (char c : rows[0].toCharArray()) {
            this.rowone.add(c);
        }
        for (char c : rows[1].toCharArray()) {
            this.rowtwo.add(c);
        }
    }

    public List<Plant> getPlantsOfStudent(String student) {
        List<Plant> plants = new ArrayList<>();
        int numberOfPlant = 0;
        for (int i = 0; i < listStudents.length; i++) {
            String name = listStudents[i];
            if (student.equals(name)) {
                numberOfPlant = i * 2;
            }
        }
        plants.add(Plant.getPlant(this.rowone.get(numberOfPlant)));
        plants.add(Plant.getPlant(this.rowone.get(numberOfPlant + 1)));
        plants.add(Plant.getPlant(this.rowtwo.get(numberOfPlant)));
        plants.add(Plant.getPlant(this.rowtwo.get(numberOfPlant + 1)));
        return plants;
    }
}