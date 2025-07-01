import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class AdjacencyListIntDigraph<T extends Comparable<? super T>> implements Graph<T> {
    private int V;
    private int E;
    private TreeMap<T, Integer> map;
    private T[] keys;
    private List<Integer>[] adj;

    public AdjacencyListIntDigraph(int V){
        this.V = 0;
        this.E = 0;
        map = new TreeMap<>();
        keys = (T[]) new Comparable[V];
        adj = new LinkedList[V];
    }

    /**
     * @post Returns the number of vertices in this graph. */
    public int V(){
        return V;
    }

    /**
     * @post Return the number of edges in this graph. */
    public int E(){
        return E;
    }

    /**
     * @pre v is beetwen 
     * @post Return the list of adjacents associated whit vertex v. */
    public List<Integer> adj(int v){
        if(v<0 || v >= V)
            throw new IllegalArgumentException();
        
        return adj[v];
    }

    /**
     * @pre V >= 0
     * @post Returns the name of the vertex associated with te integer v. */
    public T nameOf(int v){
        if(v<0) throw new IllegalArgumentException();

        return keys[v];
    }

    /**
     * @pre containsVertex(v).
     * @post Return the integer associated with the vertex named v. */
    public int indexOf(T v){
        if(!containsVertex(v)) throw new IllegalArgumentException();

        return map.get(v);
    }

    /**
     * @post Return true iff there is a vertex with label v 
     * in this graph. */
    public boolean containsVertex(T v){
        return map.containsKey(v);
    }

     /**
     * @pre !containsVertex(v).
     * @post Adds the vertex with label v to this graph. */
    public void addVertex(T v){
        if(containsVertex(v))
            throw new IllegalArgumentException("El vertice v ya existe en el grafo");
        if(V >= keys.length)
            throw new IllegalStateException();
        
        keys[V] = v;
        map.put(v, V);
        adj[V] = new LinkedList<>(); 
        V++;
    }

    /**
     * @pre v and w are vertices of the graph.
     * @post Adds the undirected edge v-w to this graph. */
    public void addEdge(T v, T w){
        if(!containsVertex(v) || !containsVertex(w)) throw new IllegalArgumentException();

        E++;
        adj[indexOf(v)].add(indexOf(w));
    }

}
