import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    private EdgeWeightedIntDigraph G;
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    //private PriorityQueue<Node> pq;
    private IndexMinPQ<Double> pq;
    private List<Integer> recorrido;

    /**
     * 
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
    */

    public Dijkstra(EdgeWeightedIntDigraph G, int s){
        if(s<0 || s>=G.V()) throw new IllegalArgumentException();
        
        this.G = G;
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new IndexMinPQ<>(G.V());
        recorrido = new LinkedList<>();

        for(int v = 0; v<G.V(); v++){
            if(v != s)
                distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;
        pq.insert(s, distTo[s]);

        while(!pq.isEmpty()){
            int v = pq.delMin();
            recorrido.add(v);
            
            for(DirectedEdge e: G.adj(v)){
                relax(e);
            }
        }
    }

    public List<Integer> getRecorrido() {
        return recorrido;
    }


    private void relax(DirectedEdge e){
        int v = e.from;
        int w = e.to;
        if(distTo[w] > distTo[v] + e.weight){
            distTo[w] = distTo[v] + e.weight;
            edgeTo[w] = e;
            if(pq.contains(w)) 
                //Si el vertice ya fue añadido a la cola, actualizar su distancia.
                pq.decreaseKey(w, distTo[w]);
            else
                //Agrega un nuevo vertice con su nueva distancia. 
                pq.insert(w, distTo[w]);
        } 
    }

    public static void main(String[] args){
        EdgeWeightedIntDigraph G = new EdgeWeightedIntDigraph(6);
        G.addEdge(0, 1, 4);
        G.addEdge(0, 2, 2);
        G.addEdge(1, 3, 5);
        G.addEdge(2, 1, 1);
        G.addEdge(2, 3, 8);
        G.addEdge(2, 4, 10);
        G.addEdge(3, 4, 2);
        G.addEdge(3, 5, 6);
        G.addEdge(4, 5, 2);

        //Reocorrido esperado 0->2->1->3->4->5

        Dijkstra camino = new Dijkstra(G, 0);
        int paso = 1;
        for(int v: camino.getRecorrido()){
            System.out.println("Paso " + paso + ": "  + v);
            paso++;
        }
    }

}
