import java.util.ArrayList;
import java.util.List;

class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    private List<T> asSortedList;
    private List<T> asLevelSortedList;    

    void insert(T value) {        
        if (this.root == null){
            this.root = new Node<>(value);
            asLevelSortedList = new ArrayList<>();
        }else {
            this.root.insert(value);
        }
        asLevelSortedList.add(value);
    }

    List<T> getAsSortedList() {
        asSortedList = new ArrayList<>();
        shutSortedList();
        return asSortedList;
    }

    void shutSortedList(){
         sortedList(root);
    }
    void sortedList(Node<T> data){
        if (data == null) return;
        else {
            sortedList(data.getLeft());
            asSortedList.add(data.getData());
            sortedList(data.getRight());
        }
    }
    List<T> getAsLevelOrderList() {
        return asLevelSortedList;
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

        void insert(T data){
            if(data.compareTo(this.data) <= 0){
                if (this.left == null){
                    this.left = new Node<>(data);
                }else {
                    this.left.insert(data);
                }
            }else {
                if (this.right == null){
                    this.right = new Node<>(data);
                }else {
                    this.right.insert(data);
                }
            }
        }
    }
}