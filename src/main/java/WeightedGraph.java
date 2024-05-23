import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WeightedGraph<V> {
    private final boolean directed;
    private final Map<V, Vertex<V>> vertices;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> sourceVertex = vertices.computeIfAbsent(source, Vertex::new);
        Vertex<V> destVertex = vertices.computeIfAbsent(dest, Vertex::new);

        sourceVertex.addAdjacentVertex(destVertex, weight);
        if (!directed) {
            destVertex.addAdjacentVertex(sourceVertex, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Set<V> getVertices() {
        return vertices.keySet();
    }
}
