
public class Prim {
    private EdgeWeightedIntGraph G;
    private double[] distTo;
    private UndirectedEdge[] edgeTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public Prim(EdgeWeightedIntGraph G, int s){
        this.G = G;
        distTo = new double[G.V()];
        edgeTo = new UndirectedEdge[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());

        algoritmoPrim(G, s);
        MST();
    }

    /**
     * @post almacena en edgeTo y distTo el recorrido hecho por prim usando las aristas de peso minimo.
     */
    private void algoritmoPrim(EdgeWeightedIntGraph G, int v){
        //Inicializacion de la cola de prioridad.
        for(int w = 0; w < G.V(); w++){
            if(w != v)
                distTo[w] = Double.POSITIVE_INFINITY;
                pq.insert(w, distTo[w]);
        }

        distTo[v] = 0;
        pq.insert(v, distTo[v]);
        
        while(!pq.isEmpty()){
            int currentVer = pq.delMin();
            marked[currentVer] = true;

            for(UndirectedEdge e: G.adj(currentVer)){
                int w = e.other(currentVer);
                if(!marked[w]){
                    actualizar(e);
                }
                
            }
        }
    }

    /**
     * @post actualiza la distancia de un vertice en el caso que se encuentre una adyacencia con mejor peso
     */
    private void actualizar(UndirectedEdge e){
        int v = e.either(); int w = e.other(v);
        if(distTo[w] > e.weight()){
            distTo[w] = e.weight();
            edgeTo[w] = e;
            pq.decreaseKey(w, distTo[w]);
        }
    }

    /**
     * @post retorna el peso total del arbol abarcador minimo
     * este metodo debe ser llamado luego de ejecutar el algoritmo de prim.
     */
    private int MST(){
        int weight = 0;
        for(UndirectedEdge e: edgeTo){
            if(e != null)
                weight += e.weight();
        }

        return weight;
    }


}
