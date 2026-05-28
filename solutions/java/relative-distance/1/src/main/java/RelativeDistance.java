import java.util.*;

class RelativeDistance {

    private final Map<String, Set<String>> graph = new HashMap<>();

    public RelativeDistance(Map<String, List<String>> familyTree) {
        for (Map.Entry<String, List<String>> entry : familyTree.entrySet()) {
            String parent = entry.getKey();
            List<String> children = entry.getValue();

            graph.putIfAbsent(parent, new HashSet<>());

            for (String child : children) {
                graph.putIfAbsent(child, new HashSet<>());

                graph.get(parent).add(child);
                graph.get(child).add(parent);
            }


            for (int i = 0; i < children.size(); i++) {
                for (int j = i + 1; j < children.size(); j++) {
                    String childA = children.get(i);
                    String childB = children.get(j);

                    graph.get(childA).add(childB);
                    graph.get(childB).add(childA);
                }
            }
        }
    }

    public int degreeOfSeparation(String personA, String personB) {
        if (!graph.containsKey(personA) || !graph.containsKey(personB)) {
            return -1;
        }

        Queue<PersonLevel> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new PersonLevel(personA, 0));
        visited.add(personA);

        while (!queue.isEmpty()) {
            PersonLevel current = queue.poll();

            if (current.name.equals(personB)) {
                return current.level;
            }

            for (String neighbor : graph.get(current.name)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new PersonLevel(neighbor, current.level + 1));
                }
            }
        }

        return -1;
    }

    private static class PersonLevel {
        String name;
        int level;

        PersonLevel(String name, int level) {
            this.name = name;
            this.level = level;
        }
    }
}

