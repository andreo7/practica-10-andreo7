import java.util.LinkedList;
import java.util.List;

public class EdgeWeightedIntDigraph {
    private int V;
    private int E;
    private List<DirectedEdge>[] adj;

    public EdgeWeightedIntDigraph(int V){
        this.V = V;
        this.E = 0;
        adj = new LinkedList[V];
        for(int v = 0; v < V; v++){
            adj[v] = new LinkedList<>();
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
     * @pre v and w are vertices of the graph.
     * @post Adds the directed edge v->w to this graph. */
    public void addEdge(int v, int w, double weight){
        if(v<0 ||v >= V) throw new IllegalArgumentException();
        if(w<0 || w >= V) throw new IllegalArgumentException();

        DirectedEdge wid = new DirectedEdge(v, w, weight); //from: v, to: w.
        adj[v].add(wid);
        E++;
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
    public List<DirectedEdge> allEdges(){
        List<DirectedEdge> edges = new LinkedList<>();
        for(int v = 0; v<V; v++){
            for(DirectedEdge e: adj(v)){
                edges.add(e);
            }
        }

        return edges;
    }
}

