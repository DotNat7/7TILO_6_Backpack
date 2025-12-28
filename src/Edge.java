public class Edge {
    private final int uid;
    private final int source;
    private final int target;
    private final int weight;

    public Edge(int uid, int source, int target, int weight) {
        this.uid = uid;
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public int getUid() { return uid; }
    public int getSource() { return source; }
    public int getTarget() { return target; }
    public int getWeight() { return weight; }
}