import java.util.List;
public class BinarySearch {
    private final List<Integer> sortedList;
    public BinarySearch(List<Integer> sortedList) {
        this.sortedList = sortedList;
    }
    public int indexOf(int value) throws ValueNotFoundException {
        int left = 0;
        int right = sortedList.size() - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (sortedList.get(middle) == value) {
                return middle;
            } else if (sortedList.get(middle) < value) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        throw new ValueNotFoundException("Value not in array");
    }
}