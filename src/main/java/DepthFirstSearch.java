import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private final Map<V, V> edgeTo;
    private final Set<V> marked;
    private final V source;

    public DepthFirstSearch(MyGraph<V> graph, V source) {
        edgeTo = new HashMap<>();
        marked = new HashSet<>();
        this.source = source;
        dfs(graph, source);
    }

    private void dfs(MyGraph<V> graph, V vertex) {
        marked.add(vertex);
        for (V adj : graph.getAdjacentVertices(vertex)) {
            if (!marked.contains(adj)) {
                edgeTo.put(adj, vertex);
                dfs(graph, adj);
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
