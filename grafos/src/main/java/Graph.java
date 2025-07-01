import java.util.List;

public interface Graph {
    
    /**
     * @post Returns the number of vertices in this graph. */
    public int V();

    /**
     * @post Return the number of edges in this graph. */
    public int E();

    /**
     * @pre 0 <= v < V && 0 <= w < V
     * @post Adds edge between vertices v and w to this graph. */
    public void addEdge(int v, int w);

}
