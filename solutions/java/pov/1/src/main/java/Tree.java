import java.util.*;

class Tree {
    private final String label;
    private final List<Tree> children;
    private Tree parent; 

    public Tree(String label) {
        this(label, new ArrayList<>());
    }

    public Tree(String label, List<Tree> children) {
        this.label = label;
        this.children = children;
        children.forEach(c -> c.parent = this);
    }

    public static Tree of(String label) {
        return new Tree(label);
    }

    public static Tree of(String label, List<Tree> children) {
        return new Tree(label, children);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tree tree = (Tree) o;
        return label.equals(tree.label)
                && children.size() == tree.children.size()
                && children.containsAll(tree.children)
                && tree.children.containsAll(children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, children);
    }

    @Override
    public String toString() {
        return "Tree{" + label +
                ", " + children +
                "}";
    }

    public Tree fromPov(String fromNode) {
        Tree originPov = findLabel(fromNode);
        if (originPov == null) {
            throw new UnsupportedOperationException("Tree could not be reoriented");
        }
        Tree result = new Tree(originPov.label);
        final Set<String> done = new HashSet<>();
        done.add(fromNode);
        fillNode(result, originPov, done);

        return result;
    }

    private void fillNode(Tree result, Tree origin, Set<String> done) {
        // Parent becomes a child
        if (origin.parent != null && !done.contains(origin.parent.label)) {
            Tree child = new Tree(origin.parent.label);
            result.children.add(child);
            done.add(child.label);
            fillNode(child, origin.parent, done);
        }

        for (Tree c : origin.children) {
            if (!done.contains(c.label)) {
                Tree child = new Tree(c.label);
                result.children.add(child);
                done.add(c.label);
                fillNode(child, c, done);
            }
        }
    }

    public List<String> pathTo(String fromNode, String toNode) {
        List<String> result;
        final Tree from = findLabel(fromNode);
        final Tree to = findLabel(toNode);
        if (from == null || to == null) {
            throw new UnsupportedOperationException("No path found");
        }
        result = getPath(List.of(from.label), from, toNode);
        return result;
    }

    private List<String> getPath(
            List<String> path,
            Tree location, String target) {
        final Tree p = location.parent;

        List<String> upResult = null;
        List<List<String>> downResult = new ArrayList<>();

        if (p != null && !path.contains(p.label)) {
            List<String> l = new ArrayList<>(path);
            l.add(p.label);

            if (p.label.equals(target)) {
                upResult = l;
            } else {
                upResult = getPath(l, p, target);
            }
        }

        for (Tree c : location.children) {
            if (!path.contains(c.label)) {
                List<String> l = new ArrayList<>(path);
                l.add(c.label);
                if (c.label.equals(target)) {
                    downResult.add(l);
                } else {
                    downResult.add(getPath(l, c, target));
                }
            }
        }

        if (upResult != null && upResult.contains(target)) {
            return upResult;
        }

        for (List<String> dr : downResult) {
            if (dr != null && dr.contains(target)) {
                return dr;
            }
        }
        return null;
    }

    public Tree findLabel(String lbl) {
        if (lbl.equals(label)) {
            return this;
        } else {
            Tree result;
            for (Tree child : children) {
                result = child.findLabel(lbl);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}