import java.util.LinkedList;
import java.util.List;

public class EdgeWeightedIntGraph implements WeightedGraph {
    private int V;
    private int E;
    private List<Edge>[] adj;

    public EdgeWeightedIntGraph(int V){
        this.V = V;
        this.E = 0;
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
     * @pre v and w are vertices of the graph.
     * @post Adds the unidirected edge v-w to this graph. */
    public void addEdge(int v, int w, double weight){
        if(v<0 ||v >= V) throw new IllegalArgumentException();
        if(w<0 || w >= V) throw new IllegalArgumentException();

        adj[v].add(new Edge(v, w, weight));
        adj[w].add(new Edge(w, v, weight));
    }

     /**
     * @post return the list of adjayents of vertex v.
     */
    public List<Edge> adj(int v){
        if(v<0 ||v >= V) throw new IllegalArgumentException();

        return adj[v];
    }
    
    /**
     * @post return the list of all edges of this graph.
     */
    public List<Edge> edges(){
        List<Edge> edges = new LinkedList<>();
        for(int v = 0; v<V; v++){
            for(Edge e: adj(v)){
                int w = e.other(v);
                if(v < w)
                    edges.add(e);
            }
        }

        return edges;
    }
}
