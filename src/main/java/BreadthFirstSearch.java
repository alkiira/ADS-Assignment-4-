import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private final Map<V, V> edgeTo;
    private final Set<V> marked;
    private final V source;

    public BreadthFirstSearch(MyGraph<V> graph, V source) {
        edgeTo = new HashMap<>();
        marked = new HashSet<>();
        this.source = source;
        bfs(graph, source);
    }

    public BreadthFirstSearch(WeightedGraph<V> graph, V source) {
        edgeTo = new HashMap<>();
        marked = new HashSet<>();
        this.source = source;
        bfs(graph, source);
    }

    private void bfs(MyGraph<V> graph, V source) {
        Queue<V> queue = new LinkedList<>();
        queue.add(source);
        marked.add(source);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            for (V adj : graph.getAdjacentVertices(current)) {
                if (!marked.contains(adj)) {
                    marked.add(adj);
                    edgeTo.put(adj, current);
                    queue.add(adj);
                }
            }
        }
    }

    private void bfs(WeightedGraph<V> graph, V source) {
        Queue<V> queue = new LinkedList<>();
        queue.add(source);
        marked.add(source);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            Vertex<V> vertex = graph.getVertex(current);
            for (Vertex<V> adj : vertex.getAdjacentVertices().keySet()) {
                V adjData = adj.getData();
                if (!marked.contains(adjData)) {
                    marked.add(adjData);
                    edgeTo.put(adjData, current);
                    queue.add(adjData);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        return marked.contains(vertex);
    }

    @Override
    public List<V> pathTo(V vertex) {
        if (!hasPathTo(vertex)) return null;
        List<V> path = new LinkedList<>();
        for (V x = vertex; x != null && !x.equals(source); x = edgeTo.get(x)) {
            path.add(0, x);
        }
        path.add(0, source);
        return path;
    }
}
