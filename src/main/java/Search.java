import java.util.List;

public interface Search<V> {
    boolean hasPathTo(V vertex);
    List<V> pathTo(V vertex);
}
