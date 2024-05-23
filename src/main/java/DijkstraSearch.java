import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final Map<V, Double> distTo;
    private final Map<V, V> edgeTo;
    private final PriorityQueue<VertexDistance<V>> priorityQueue;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        priorityQueue = new PriorityQueue<>(Comparator.comparing(VertexDistance::getDistance));

        for (V vertex : graph.getVertices()) {
            distTo.put(vertex, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);

        priorityQueue.add(new VertexDistance<>(source, 0.0));

        while (!priorityQueue.isEmpty()) {
            VertexDistance<V> vertexDistance = priorityQueue.poll();
            V vertex = vertexDistance.getVertex();
            double distance = vertexDistance.getDistance();
            if (distance > distTo.get(vertex)) continue;

            Vertex<V> currentVertex = graph.getVertex(vertex);
            for (Map.Entry<Vertex<V>, Double> entry : currentVertex.getAdjacentVertices().entrySet()) {
                relax(entry.getKey().getData(), entry.getValue(), vertex);
            }
        }
    }

    private void relax(V neighbor, double weight, V vertex) {
        double newDist = distTo.get(vertex) + weight;
        if (newDist < distTo.get(neighbor)) {
            distTo.put(neighbor, newDist);
            edgeTo.put(neighbor, vertex);
            priorityQueue.add(new VertexDistance<>(neighbor, newDist));
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        return distTo.containsKey(vertex) && distTo.get(vertex) < Double.POSITIVE_INFINITY;
    }

    @Override
    public List<V> pathTo(V vertex) {
        if (!hasPathTo(vertex)) return null;
        List<V> path = new LinkedList<>();
        for (V x = vertex; x != null && !x.equals(edgeTo.get(x)); x = edgeTo.get(x)) {
            path.add(0, x);
        }
        path.add(0, edgeTo.get(vertex));
        return path;
    }

    private static class VertexDistance<V> {
        private final V vertex;
        private final double distance;

        public VertexDistance(V vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public V getVertex() {
            return vertex;
        }

        public double getDistance() {
            return distance;
        }
    }
}
