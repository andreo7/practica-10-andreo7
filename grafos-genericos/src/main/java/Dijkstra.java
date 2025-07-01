import java.util.PriorityQueue;

public class Dijkstra {
    private EdgeWeightedIntDigraph G;
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private PriorityQueue<Node> pq;

    private class Node implements Comparable<Node>{
        int vertice;
        double distancia;

        private Node(int vertice, double distancia){
            this.vertice = vertice;
            this.distancia = distancia;
        }

        public int compareTo(Node other){
            return Double.compare(this.distancia, other.distancia);
        }
    }

    public Dijkstra(EdgeWeightedIntDigraph G, int s){
        if(s<0 || s>=G.V()) throw new IllegalArgumentException();
        
        this.G = G;
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new PriorityQueue<>();

        for(int v = 0; v<G.V(); v++){
            if(v != s)
                distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;
        pq.add(new Node(s, distTo[s]));

        while(!pq.isEmpty()){
            int v = pq.poll().vertice;
            
            for(DirectedEdge e: G.adj(v)){
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e){
        int v = e.from;
        int w = e.to;
        if(distTo[w] > distTo[v] + e.weight){
            distTo[w] = distTo[v] + e.weight;
            edgeTo[w] = e;
            pq.add(new Node(w, distTo[w])); //Agrego un nodo a la cola solo si mejora el camino.
        } 
    }
}
