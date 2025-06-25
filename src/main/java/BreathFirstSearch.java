import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreathFirstSearch<T extends Comparable<? super T>> {
    private boolean[] marked;
    private int count;
    private AdjacencyListGraph<T> G;
    private T v;
    List<Integer> recorrido;

    public BreathFirstSearch(AdjacencyListGraph<T> G, T v){
        this.G = G;
        this.v = v;
        count = 0;
        marked = new boolean[G.V()];
        recorrido = new LinkedList<>();
        bfs(G, G.indexOf(v));
    }

    private List<Integer> bfs(AdjacencyListGraph<T> G, int s){
        Queue<Integer> c = new LinkedList<>();
        c.add(s);
        marked[s] = true;
        while(!c.isEmpty()){
            int x = c.remove();
            recorrido.add(x);
            count++;

            for(int w: G.adj(x)){
                if(!marked[w])
                    c.add(w);
                    marked[w] = true;
            }
        }

        return recorrido;
    }

    public int count(){
        return count;
    } 

    public boolean conexo(){
        return G.V() == count;
    }

    public List<Integer> getRecorrido(){
        return recorrido;
    }

    public static void main(String[] args){
        AdjacencyListGraph<String> G = new AdjacencyListGraph<>(7);
        G.addVertex("a"); //0
        G.addVertex("b"); //1
        G.addVertex("c"); //2
        G.addVertex("d"); //3
        G.addVertex("e"); //4
        G.addVertex("f"); //5
        G.addVertex("g"); //6
        G.addEdge("a", "b");
        G.addEdge("a", "c");
        G.addEdge("b", "d");
        G.addEdge("b", "e");
        G.addEdge("c", "f");
        G.addEdge("c", "g");

        BreathFirstSearch<String> search = new BreathFirstSearch<String>(G, "a");
        for(int v: search.getRecorrido()){
            System.out.println(G.nameOf(v));
        }

        System.out.println("\nCantidad de vértices visitados: " + search.count());
        System.out.println("¿El grafo es conexo desde 'a'? " + search.conexo());
    }
}
