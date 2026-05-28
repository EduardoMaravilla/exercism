import java.util.*;
import java.util.stream.Collectors;

class School {
    Map<String, Integer> students = new HashMap<>();

    boolean add(String student, int grade) {
        if (students.containsKey(student)) {
            return false;
        }
        students.put(student, grade);
        return true;
    }

    List<String> roster() {
        return students.entrySet().stream().sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).thenComparing(Map.Entry<String, Integer>::getKey)).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    List<String> grade(int grade) {
        return students.entrySet().stream().filter(entry -> entry.getValue() == grade).map(Map.Entry::getKey).sorted().collect(Collectors.toList());
    }
}