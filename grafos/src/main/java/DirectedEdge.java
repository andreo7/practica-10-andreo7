public class DirectedEdge implements Comparable<DirectedEdge>, Edge {
    final int from;
    final int to;
    final double weight;

    public DirectedEdge(int from, int to, double weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getFrom(){
        return from;
    }

    public int getTo(){
        return to;
    }

    public double weight(){
        return weight;
    }

    // Para poder comparar aristas por peso
    @Override
    public int compareTo(DirectedEdge that) {
        return Double.compare(this.weight, that.weight);
    }
}
