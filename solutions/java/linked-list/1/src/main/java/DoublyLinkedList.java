class DoublyLinkedList<T> {
    private Element<T> head;
    private Element<T> tail;

    void push(T value) {
        Element<T> newElement = new Element<>(value, tail, null);
        if (tail != null) {
            tail.next = newElement;
        }
        tail = newElement;
        if (head == null) {
            head = newElement;
        }
    }

    T pop() {
        T value = tail.value;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        return value;
    }

    void unshift(T value) {
        Element<T> newElement = new Element<>(value, null, head);
        if (head != null) {
            head.prev = newElement;
        }
        head = newElement;
        if (tail == null) {
            tail = newElement;
        }
    }

    T shift() {
        T value = head.value;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        return value;
    }

    private static final class Element<T> {
        private final T value;
        private Element<T> prev;
        private Element<T> next;

        Element(T value, Element<T> prev, Element<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
