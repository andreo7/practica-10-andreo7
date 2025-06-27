public interface Graph<T extends Comparable<? super T>> {
    
    /**
     * @post Returns the number of vertices in this graph. */
    public int V();

    /**
     * @post Return the number of edges in this graph. */
    public int E();

    /**
     * @pre V >= 0
     * @post Returns the name of the vertex associated with te integer v. */
    public T nameOf(int v);

    /**
     * @pre containsVertex(v).
     * @post Return the integer associated with the vertex named v. */
    public int indexOf(T v);

    /**
     * @pre !containsVertex(v).
     * @post Adds the vertex with label v to this graph. */
    public void addVertex(T v);

    /**
     * @post Return true iff there is a vertex with label v 
     * in this graph. */
    public boolean containsVertex(T v);

}
