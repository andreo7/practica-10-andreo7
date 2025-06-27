public class BellmanFord {
    private EdgeWeightedIntDigraph G;
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public BellmanFord(EdgeWeightedIntDigraph G, int s){
        if(s<0 || s>= G.V()) 
            throw new IllegalArgumentException();
        
        this.G = G;
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for(int v = 0; v<G.V(); v++){
            if(v != s)
                distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;
        
        bellmanFord(G, s);
    }

    private boolean bellmanFord(EdgeWeightedIntDigraph G, int v){
        distTo[v] = 0;
        for(int j = 1; j<G.V(); j++){
            for(DirectedEdge e: G.allEdges()){
                relax(e);
            }
        }

        for(DirectedEdge e: G.allEdges()){
            int k = e.from; int w = e.to;
            if(distTo[w] > distTo[k] + e.weight)
                return false;
        }

        return true;
    }

    private void relax(DirectedEdge e){
        int v = e.from; int w = e.to;
        if(distTo[w] > distTo[v] + e.weight){
            distTo[w] = distTo[v] + e.weight;
            edgeTo[w] = e;
        }
    }
}
