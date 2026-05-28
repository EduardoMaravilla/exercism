import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

class ListOps {

    static <T> List<T> append(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>();
        for (T element : list1) {
            result.add(element);
        }
        for (T element : list2) {
            result.add(element);
        }
        return result;
    }

    static <T> List<T> concat(List<List<T>> listOfLists) {
        List<T> result = new ArrayList<>();
        for (List<T> elements : listOfLists) {
            for (T element: elements){
                result.add(element);
            }            
        }        
        return result;   
    }

    static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T element : list) {
            if (predicate.test(element)){
                result.add(element);
            }
        }
        return result;
    }

    static <T> int size(List<T> list) {
        return list.size();
    }

    static <T, U> List<U> map(List<T> list, Function<T, U> transform) {
        List<U> result = new ArrayList<>();
        for (T element : list) {
            result.add(transform.apply(element));
        }
        return result;
    }

    static <T> List<T> reverse(List<T> list) {
        List<T> result = new ArrayList<>();
        for (int i = list.size()-1; i >= 0; i--) {
               result.add(list.get(i));
        }
        return result;
    }

    static <T, U> U foldLeft(List<T> list, U initial, BiFunction<U, T, U> f) {
        U accumulator = initial;
        for (T element : list) {
            accumulator = f.apply(accumulator, element);
        }
        return accumulator;
    }

    static <T, U> U foldRight(List<T> list, U initial, BiFunction<T, U, U> f) {
        List<T> reverse = reverse(list);
        U accumulator = initial;
        for (T element : reverse) {
            accumulator = f.apply(element, accumulator);
        }
        return accumulator;
    }

    private ListOps() {
        // No instances.
    }

}
