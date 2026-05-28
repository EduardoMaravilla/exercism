import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    private List<T> asSortedList;
    private List<T> asLevelSortedList;

    void insert(T value) {
        if (this.root == null) {
            this.root = new Node<>(value);
        } else {
            this.root.insert(value);
        }
    }

    List<T> getAsSortedList() {
        asSortedList = new ArrayList<>();
        shutSortedList();
        return asSortedList;
    }

    void shutSortedList() {
        sortedList(getRoot());
    }

    void sortedList(Node<T> data) {
        if (data == null) return;     
        sortedList(data.getLeft());
        asSortedList.add(data.getData());
        sortedList(data.getRight());
    }

    List<T> getAsLevelOrderList() {
        asLevelSortedList = new ArrayList<>();
        shutLevelSortedList(getRoot());
        return asLevelSortedList;
    }

    void shutLevelSortedList(Node<T> data) {
        if (data == null) return;
        Queue<Node<T>> val= new LinkedList<>();
        val.offer(data);
        while (!val.isEmpty()){
            Node<T> temp= val.poll();
            asLevelSortedList.add(temp.getData());
            if (temp.getLeft() != null){
                val.offer(temp.getLeft());
            }
            if (temp.getRight() != null){
                val.offer(temp.getRight());
            }
        }
    }

    Node<T> getRoot() {
        return root;
    }

    static class Node<T extends Comparable<T>> {

        T data;
        Node<T> left;
        Node<T> right;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }

        T getData() {
            return data;
        }

        void insert(T data) {
            if (data.compareTo(this.data) <= 0) {
                if (this.left == null) {
                    this.left = new Node<>(data);
                } else {
                    this.left.insert(data);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node<>(data);
                } else {
                    this.right.insert(data);
                }
            }
        }
    }
}