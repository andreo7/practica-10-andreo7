public class FloydWarshall {
    private EdgeWeightedIntDigraph G;
    private double[][] distTo;
    private DirectedEdge[][] edgeTo;

    public FloydWarshall(EdgeWeightedIntDigraph G){
        this.G = G;
        distTo = new double[G.V()][G.V()];
        edgeTo = new DirectedEdge[G.V()][G.V()];

        for(int v = 0; v < G.V(); v++){
            distTo[v][v] = 0;
            for(int w = 0; w < G.V(); w++){
                if(w != v)
                    distTo[v][w] = Double.POSITIVE_INFINITY;
            }
        }

        for(int i = 0; i<G.V(); i++){
            for(DirectedEdge e: G.adj(i)){
                int v = e.from; int w = e.to;
                distTo[v][w] = e.weight;
                edgeTo[v][w] = e;
            }
        }

        for(int k = 0; k<G.V(); k++){
            for(int i = 0; i<G.V(); i++){
                for(int j = 0; j<G.V(); j++){
                    if(distTo[i][j] > distTo[i][k] + distTo[k][j])
                        distTo[i][j] = distTo[i][k] + distTo[k][j];
                        edgeTo[i][j] = edgeTo[k][j];
                }
            }
        }
    }
}
