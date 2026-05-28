public class DoublyLinkedList<T> {
    private Element<T> head;
    private Element<T> tail;

    void push(T value) {
        Element<T> prev = tail;
        Element<T> newElement = new Element<>(value, prev, null);
        tail = newElement;
        if (prev == null) head = newElement;
        else prev.next = newElement;
    }

    T pop() {
        T value = tail.value;
        Element<T> prev = tail.prev;
        tail.value = null;
        tail.prev = null;
        tail = prev;
        if (prev == null) head = null;
        else prev.next = null;
        return value;
    }

    void unshift(T value) {
        Element<T> next = head;
        Element<T> newElement = new Element<>(value, null, next);
        head = newElement;
        if (next == null) tail = newElement;
        else next.prev = newElement;
    }

    T shift() {
        T value = head.value;
        Element<T> next = head.next;
        head.value = null;
        head.next = null;
        head = next;
        if (next == null) tail = null;
        else next.prev = null;
        return value;
    }

    private static final class Element<T> {
        private T value;
        private Element<T> prev;
        private Element<T> next;

        Element(T value, Element<T> prev, Element<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}