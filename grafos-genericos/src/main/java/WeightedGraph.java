import java.util.List;

public interface WeightedGraph<T extends Comparable<? super T>> {
    /**
     * @post Returns the number of vertices in this graph. */
    public int V();

    /**
     * @post Return the number of edges in this graph. */
    public int E();

}
