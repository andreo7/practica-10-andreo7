import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class EdgeWeightedIntDigraph<T extends Comparable<? super T>> implements Graph<T> {
    private int V;
    private int E;
    private TreeMap<T, Integer> map;
    private T[] keys;
    private List<DirectedEdge>[] adj;

    public EdgeWeightedIntDigraph(int V){
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
        if(!containsVertex(v)) 
            throw new IllegalArgumentException("El vertice v debe existir en el grafo");

        return map.get(v);
    }

    /**
     * @pre !containsVertex(v).
     * @post Adds the vertex with label v to this graph. */
    public void addVertex(T v){
        if(containsVertex(v))
            throw new IllegalArgumentException("El vertice v ya existe en este grafo");
            
        map.put(v, V);
        keys[V] = v;
        adj[V] = new LinkedList<DirectedEdge>();
        V++;
    }

    /**
     * @post Return true iff there is a vertex with label v 
     * in this graph. */
    public boolean containsVertex(T v){
        return map.containsKey(v);
    }

    /**
     * @pre v and w are vertices of the graph.
     * @post Adds the undirected edge v-w to this graph. */
    public void addEdge(T v, T w, double weight){
        if(!containsVertex(v))
            throw new IllegalArgumentException("El vertice v debe existir en el grafo");
        if(!containsVertex(w))
            throw new IllegalArgumentException("El vertice w debe existir en el grafo");
        
        int v1 = indexOf(v);
        int w1 = indexOf(w);
        DirectedEdge wid = new DirectedEdge(v1, w1, weight); //from: v1, to: w1.
        adj[v1].add(wid);
    }
}

