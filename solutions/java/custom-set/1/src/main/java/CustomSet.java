import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomSet<T> {

    private final Set<T> elements;

    CustomSet() {
        this.elements = new HashSet<>();
    }

    CustomSet(Collection<T> data) {
        this.elements = new HashSet<>(data);
    }

    boolean isEmpty() {
        return elements.isEmpty();
    }

    boolean contains(T element) {
        return elements.contains(element);
    }

    boolean isDisjoint(CustomSet<T> other) {
        return elements.stream().noneMatch(other::contains);
    }

    boolean add(T element) {
        return elements.add(element);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CustomSet)) return false;
        CustomSet<?> other = (CustomSet<?>) obj;
        return elements.equals(other.elements);
    }

    CustomSet<T> getIntersection(CustomSet<T> other) {
        Set<T> intersection = new HashSet<>(elements);
        intersection.retainAll(other.elements);
        return new CustomSet<>(intersection);
    }

    CustomSet<T> getUnion(CustomSet<T> other) {
        Set<T> union = new HashSet<>(elements);
        union.addAll(other.elements);
        return new CustomSet<>(union);
    }

    CustomSet<T> getDifference(CustomSet<T> other) {
        Set<T> difference = new HashSet<>(elements);
        difference.removeAll(other.elements);
        return new CustomSet<>(difference);
    }

    boolean isSubset(CustomSet<T> other) {
        return elements.containsAll(other.elements);
    }
}