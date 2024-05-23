import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyGraph<V> {
    private final boolean directed;
    private final Map<V, Set<V>> adjacencyList;

    public MyGraph(boolean directed) {
        this.directed = directed;
        this.adjacencyList = new HashMap<>();
    }

    public void addEdge(V source, V dest) {
        adjacencyList.computeIfAbsent(source, k -> new HashSet<>()).add(dest);
        if (!directed) {
            adjacencyList.computeIfAbsent(dest, k -> new HashSet<>()).add(source);
        }
    }

    public Set<V> getAdjacentVertices(V vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }
}
