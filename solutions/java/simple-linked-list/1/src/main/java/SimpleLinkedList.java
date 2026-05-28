import java.util.NoSuchElementException;

public class SimpleLinkedList<T> {
    Node<T> first;
    int size;

    SimpleLinkedList() {
        this.first = null;
    }

    SimpleLinkedList(T[] values) {
        for (T value : values) {
            push(value);
        }
    }

    void push(T value) {
        if (first == null) {
            first = new Node<>(value);
            size++;
            return;
        }
        Node<T> temp = first;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node<>(value);
        size++;
    }

    T pop() {
        T value = null;
        if (first == null) {
            throw new NoSuchElementException();
        } else if (first.next == null) {
            value = first.value;
            first = null;
            size--;
            return value;
        } else {
            Node<T> temp = first;
            while (temp.next != null) {
                if (temp.next.next == null) {
                    value = temp.next.value;
                    temp.next = null;
                    break;
                }
                temp = temp.next;
            }
            size--;
            return value;
        }
    }

    void reverse() {
        Node<T> reverse = null;
        int valueSize = size;
        int control= size;
        while (valueSize > 0){
            T val = pop();
            if (reverse == null){
                reverse = new Node<>(val);
            }else {
                Node<T> temp = reverse;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = new Node<>(val);
            }
            valueSize--;
        }
        first = reverse;
        size =control;
    }

    T[] asArray(Class<T> clazz) {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz,size);
        Node<T> temp = first;
        int index = size-1;
        while ( temp != null){
            array[index--] = temp.value;
            temp = temp.next;
        }
        return array;
    }

    int size() {
        return size;
    }

    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}