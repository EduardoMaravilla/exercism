import java.util.Objects;

class Zipper {
    Zipper up;
    Zipper left;
    Zipper right;

    private int value;
    Zipper(int val) {
        value = val;
    }

    BinaryTree toTree() {
        return up != null? up.toTree() : new BinaryTree(this);
    }

    int getValue() {
        return value;
    }

    void setLeft(Zipper leftChild) {
        if((left = leftChild) != null) left.up = this;
    }

    void setRight(Zipper rightChild) {
        if((right = rightChild) != null) right.up = this;
    }

    void setValue(int val) {
        value = val;
    }

    @Override
    public String toString(){
        return "value: " + value + ", left: "+
            (left == null? null: "{ " + left + " }")+ ", right: " +
            (right == null? null: "{ " + right + " }");        
    }   
}

class BinaryTree {
    
    private Zipper root;
    
    BinaryTree(int value) {
        root = new Zipper(value);
    }

    BinaryTree(Zipper root) {
        this.root = root;
    }

    Zipper getRoot() {
        return root;
    }

    String printTree() {
        return root.toString();
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true; // this object of this particulary class
        if(o == null || getClass() != o.getClass()) return false;
        BinaryTree that = (BinaryTree) o;
        return Objects.equals(that.root, root);
    }

    @Override
    public int hashCode(){
        return Objects.hash(root);
    }
}