import java.util.*;
 
public class Satellite {
    public Tree treeFromTraversals(List<Character> preorderInput, List<Character> inorderInput) {
        if (preorderInput.size() != inorderInput.size()) {
            throw new IllegalArgumentException("traversals must have the same length");
        }
        HashSet<Character> preorderSet = new HashSet<>(preorderInput);
        HashSet<Character> inorderSet = new HashSet<>(inorderInput);
        if (preorderInput.size() != preorderSet.size() || inorderInput.size() != inorderSet.size()){
            throw new IllegalArgumentException("traversals must contain unique items");
        }
        if (!preorderSet.equals(inorderSet)) {
            throw new IllegalArgumentException("traversals must have the same elements");
        }

        Map<Character, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorderInput.size(); i++) {
            inorderIndexMap.put(inorderInput.get(i), i);
        }

        return new Tree(buildTree(preorderInput, inorderInput, 0, 0, preorderInput.size(), inorderIndexMap));
    }

    private Node buildTree(List<Character> preorder, List<Character> inorder, int preStart, int inStart, int size,
                           Map<Character, Integer> inorderIndexMap) {
        if (size == 0) {
            return null;
        }

        char rootValue = preorder.get(preStart);
        Node root = new Node(rootValue);

        int rootIndex = inorderIndexMap.get(rootValue);
        int leftSize = rootIndex - inStart;
        int rightSize = size - leftSize - 1;

        root.left = buildTree(preorder, inorder, preStart + 1, inStart, leftSize, inorderIndexMap);
        root.right = buildTree(preorder, inorder, preStart + leftSize + 1, rootIndex + 1, rightSize, inorderIndexMap);

        return root;
    }
}