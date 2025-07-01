import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Kruskal {
    private EdgeWeightedIntGraph G;
    private Set<UndirectedEdge> mst;
    private int weight;

    public Kruskal(EdgeWeightedIntGraph G){
        this.G = G;
        mst = new TreeSet<>();
        weight = 0;

        algoritmoKruskal(G);
    }

    private void algoritmoKruskal(EdgeWeightedIntGraph G){
        UndirectedEdge[] edges = new UndirectedEdge[G.V()];
        int j = 0;
        for(UndirectedEdge e: G.edges()){
            edges[j++] = e;
        }
        
        Arrays.sort(edges);
        UF uf = new UF(G.V());
        for(int i = 0; i<G.V() && mst.size() < G.E() - 1; i++){
            UndirectedEdge e = edges[i];
            int v = e.either();
            int w = e.other(v);
            if(uf.find(v) != uf.find(w)){
                uf.union(v, w);
                mst.add(e);
                weight += e.weight();
            }
        }
    }
}
