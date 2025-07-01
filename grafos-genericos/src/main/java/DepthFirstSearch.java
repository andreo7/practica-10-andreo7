import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch<T extends Comparable<? super T>> {
    private boolean[] marked;
    private int count;
    private T v;
    private AdjacencyListGraph<T> G;
    List<Integer> recorrido;

    public DepthFirstSearch(AdjacencyListGraph<T> G, T v){
        this.v = v;
        this.G = G;
        marked = new boolean[G.V()];
        count = 0;
        recorrido = new LinkedList<>();
        dfs(this.G, this.G.indexOf(this.v));
    }

    private List<Integer> dfs(AdjacencyListGraph<T> G, int v){
    Stack<Integer> s = new Stack<>();
    s.push(v);
    marked[v] = true;
    while (!s.isEmpty()) {
        int x = s.pop();
        recorrido.add(x);
        count++;

        for (int w : G.adj(x)) {
            if (!marked[w]) {
                s.push(w);
                marked[w] = true; 
            }
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

    public List<Integer> recorridoCompleto(){
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

        DepthFirstSearch<String> search = new DepthFirstSearch<>(G, "a");
         System.out.println("Recorrido DFS desde 'a':");
         for (int i : search.recorridoCompleto()) {
            System.out.println(G.nameOf(i));
        }

        System.out.println("\nCantidad de vértices visitados: " + search.count());
        System.out.println("¿El grafo es conexo desde 'a'? " + search.conexo());
    }
}
