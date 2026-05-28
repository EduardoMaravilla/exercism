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

        int numplant1 = 0;
        int numplant2 = 0;
        for (int i = 0; i < listStudents.length; i++) {
            String name = listStudents[i];
            if (student.equals(name)) {
                numplant1 = i * 2;
                numplant2 = i * 2 + 1;
            }
        }
        plants.add(Plant.getPlant(this.rowone.get(numplant1)));
        plants.add(Plant.getPlant(this.rowone.get(numplant2)));
        plants.add(Plant.getPlant(this.rowtwo.get(numplant1)));
        plants.add(Plant.getPlant(this.rowtwo.get(numplant2)));

        return plants;
    }

}
