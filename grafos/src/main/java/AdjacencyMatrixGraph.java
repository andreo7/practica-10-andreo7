import java.util.LinkedList;
import java.util.List;

public class AdjacencyMatrixGraph implements Graph {
    private int V;
    private int E;
    private int[][] adj;

    public AdjacencyMatrixGraph(int V){
        this.V = V;
        this.E = 0;
        adj = new int[V][V];
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
     * @pre 0 <= v < V && 0 <= w < V
     * @post Adds the undirected edge v-w to this graph. */
    public void addEdge(int v, int w){
        if(v<0 || v >= V) throw new IllegalArgumentException();
        if(w<0 || w >= V) throw new IllegalArgumentException();

        adj[v][w] = 1;
        adj[w][v] = 1;
        E++;
    }

    /**
     * @pre 0 <= v < V
     * @post Return the list of adjacents associated whit vertex v. */
    public List<Integer> adj(int v){
       if(v<0 || v >= V)
            throw new IllegalArgumentException();
            
       List<Integer> adyacentes = new LinkedList<>();
       for(int i = 0; i<V; i++){
            if(adj[v][i] != 0)
                adyacentes.add(i);  
       } 

       return adyacentes;
    }
}
