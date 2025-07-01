import java.util.LinkedList;
import java.util.List;

public class AdjacencyListIntDigraph implements Graph {
       private int V;
    private int E;
    private List<Integer>[] adj;

    public AdjacencyListIntDigraph(int V){
        if(V<0) throw new IllegalArgumentException();

        this.V = V;
        this.E = 0;
        adj = new LinkedList[V];
        for(int i = 0; i<V; i++){
            adj[i] = new LinkedList<>();
        }
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
     * @pre 0 <= v < V
     * @post Return the list of adjacents associated whit vertex v. */
    public List<Integer> adj(int v){
        if(v<0 || v >= V)
            throw new IllegalArgumentException();
        
        return adj[v];
    }

    /**
     * @pre 0 <= v < V && 0 <= w < V
     * @post Adds the directed edge v->w to this graph. */
    public void addEdge(int v, int w){
        if(v<0 || v >= V) throw new IllegalArgumentException();
        if(w<0 || w >= V) throw new IllegalArgumentException();

        adj[v].add(w);
        E++;
    }
}
