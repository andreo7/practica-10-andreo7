import java.util.List;

public interface WeightedGraph {
    /**
     * @post Returns the number of vertices in this graph. */
    public int V();

    /**
     * @post Return the number of edges in this graph. */
    public int E();

    /**
     * @pre v and w are vertices of the graph.
     * @post Adds the directed edge v->w to this graph. */
    public void addEdge(int v, int w, double weight);
}
