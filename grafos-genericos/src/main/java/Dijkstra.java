import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra<T extends Comparable<? super T>> {
    private EdgeWeightedIntDigraph<T> G;
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private PriorityQueue<Node> pq;
    private List<Integer> recorrido;

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

    public Dijkstra(EdgeWeightedIntDigraph<T> G, T s){
        if(!G.containsVertex(s)) throw new IllegalArgumentException();
        
        this.G = G;
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new PriorityQueue<>();
        recorrido = new LinkedList<>();
        
        int v = G.indexOf(s);

        algoritmoDijkstra(G, v);
    }
    

    private void algoritmoDijkstra(EdgeWeightedIntDigraph<T> G, int v){
        for(int j = 0; j<G.V(); j++){
            if(j != v)
                distTo[j] = Double.POSITIVE_INFINITY;
        }
        distTo[v] = 0;
        pq.add(new Node(v, distTo[v]));
        while(!pq.isEmpty()){
            Node aux = pq.poll();
            int i = aux.vertice;
            //Si la distancia del vertice actual es mayor a la mejor distancia registrada de ese mismo vertice entonces salteamos lo que resta de ciclo.
            if(aux.distancia > distTo[i]) continue;

            recorrido.add(i);

            for(DirectedEdge e: G.adj(i)){
                relax(e);
            }
        }
    }

    public List<Integer> getRecorrido(){
        return recorrido;
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

    public static void main(String[] args){
        EdgeWeightedIntDigraph<String> G = new EdgeWeightedIntDigraph<>(6);
        G.addVertex("a");
        G.addVertex("b");
        G.addVertex("c");
        G.addVertex("d");
        G.addVertex("e");
        G.addVertex("f");

        G.addEdge("a", "b", 4);
        G.addEdge("a", "c", 2);
        G.addEdge("b", "d", 5);
        G.addEdge("c", "b", 1);
        G.addEdge("c", "d", 8);
        G.addEdge("c", "e", 10);
        G.addEdge("d", "e", 2);
        G.addEdge("d", "f", 6);
        G.addEdge("e", "f", 2);

        //Reocorrido esperado a->c->b->d->e->f

        Dijkstra<String> camino = new Dijkstra<>(G, "a");
        int paso = 1;
        for(int v: camino.getRecorrido()){
            System.out.println("Paso " + paso + ": "  + G.nameOf(v));
            paso++;
        }
    }
}
