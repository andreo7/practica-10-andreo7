public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    // Devuelve uno de los dos extremos de la arista
    public int either() {
        return v;
    }

    // Devuelve el otro extremo distinto al que le pasás
    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("El vértice no pertenece a la arista");
    }

    // Para poder comparar aristas por peso
    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }
    
}
