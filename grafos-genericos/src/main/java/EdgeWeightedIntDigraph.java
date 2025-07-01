import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class EdgeWeightedIntDigraph<T extends Comparable<? super T>> implements WeightedGraph {
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
     * @post Return true iff there is a vertex with label v 
     * in this graph. */
    public boolean containsVertex(T v){
        return map.containsKey(v);
    }

    /**
     * @pre v >= 0
     * @post Returns the name of the vertex associated with te integer v. */
    public T nameOf(int v){
        if(v<0 || v > keys.length) throw new IllegalArgumentException();

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
     * @pre !containsVertex(v).
     * @post Adds the vertex with label v to this graph. */
    public void addVertex(T v){
        if(containsVertex(v)) throw new IllegalArgumentException("El vertice ya existe en el grafo");
        if(V >= keys.length)  throw new IllegalStateException();

        keys[V] = v;
        map.put(v, V);
        adj[V] = new LinkedList<DirectedEdge>(); 
        V++;
    }

    /**
     * @pre v and w are vertices of the graph.
     * @post Adds the unidirected edge v-w to this graph. */
    public void addEdge(T v, T w, double weight){
        if(!containsVertex(v)) throw new IllegalArgumentException();
        if(!containsVertex(v)) throw new IllegalArgumentException();
        
        int vid = indexOf(v); int wid = indexOf(w);

        adj[vid].add(new DirectedEdge(vid, wid, weight));
        adj[wid].add(new DirectedEdge(wid, vid, weight));
    }

     /**
     * @post return the list of adjayents of vertex v.
     */
    public List<DirectedEdge> adj(int v){
        if(v<0 ||v >= V) throw new IllegalArgumentException();

        return adj[v];
    }

    /**
     * @post return the list of all edges of this graph.
     */
    public List<DirectedEdge> edges(){
        List<DirectedEdge> edges = new LinkedList<>();
        for(int v = 0; v<V; v++){
            for(DirectedEdge e: adj(v)){
                edges.add(e);
            }
        }

        return edges;
    }
}

