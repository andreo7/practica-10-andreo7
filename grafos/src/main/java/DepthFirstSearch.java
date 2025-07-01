import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    private Graph G; 
    List<Integer> recorrido;

    //Como los grafos dirigidos y no dirigidos implementan de graph podemos pasar como parametro cualquiera de los dos
    public DepthFirstSearch(AdjacencyListIntDigraph G, int v){
        this.G = G;
        marked = new boolean[G.V()];
        count = 0;
        recorrido = new LinkedList<>();
        dfs(G, v);
    }

    private List<Integer> dfs(AdjacencyListIntDigraph G, int v){
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
        AdjacencyListIntDigraph G = new AdjacencyListIntDigraph(6);
        G.addEdge(0, 1);
        G.addEdge(0, 3);
        G.addEdge(0, 4);
        G.addEdge(1, 3);
        G.addEdge(2, 0);
        G.addEdge(2, 3);
        G.addEdge(2, 5);
        G.addEdge(3, 0);
        G.addEdge(3, 1);
        G.addEdge(3, 2);
        G.addEdge(3, 4);
        G.addEdge(3, 5);
        G.addEdge(4, 1);
        G.addEdge(4, 5);

        DepthFirstSearch search = new DepthFirstSearch(G, 0);
         System.out.println("Recorrido DFS desde 'a':");
         for (int i : search.recorridoCompleto()) {
            System.out.println(i);
        }

        System.out.println("\nCantidad de vértices visitados: " + search.count());
        System.out.println("¿El grafo es conexo desde 'a'? " + search.conexo());
    }
}
