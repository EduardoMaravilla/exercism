import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class HighScores {
    private List<Integer> highScores;
    private List<Integer> personal;

    public HighScores(List<Integer> highScores) {
        this.highScores = highScores;
        this.personal = new ArrayList<>(highScores);
        Collections.sort(personal, Collections.reverseOrder());
    }

    List<Integer> scores() {
        return highScores;
    }

    Integer latest() {
        return highScores.get(highScores.size() - 1);
    }

    Integer personalBest() {
        return personal.get(0);
    }

    List<Integer> personalTopThree() {
        List<Integer> topThree = new ArrayList<>();
        for (int i = 0; i < personal.size() && i < 3; i++) {
            topThree.add(personal.get(i));
        }
        return topThree;
    }
}
