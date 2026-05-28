import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Graph {
    private Collection<Node> nodes;
    private Collection<Edge> edges;
    private Map<String, String> attributes;

    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.attributes = Map.of();
    }

    public Graph(Map<String, String> attributes) {
        this();
        this.attributes = Map.copyOf(attributes);
    }

    public Collection<Node> getNodes() {
        return nodes;
    }

    public Collection<Edge> getEdges() {
        return edges;
    }

    public Graph node(String name) {
        this.nodes.add(new Node(name));
        return this;
    }

    public Graph node(String name, Map<String, String> attributes) {
        this.nodes.add(new Node(name, Map.copyOf(attributes)));
        return this;
    }

    public Graph edge(String start, String end) {
        this.edges.add(new Edge(start, end));
        return this;
    }

    public Graph edge(String start, String end, Map<String, String> attributes) {
        this.edges.add(new Edge(start, end, Map.copyOf(attributes)));
        return this;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
