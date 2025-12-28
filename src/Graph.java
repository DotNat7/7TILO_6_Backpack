import java.util.*;

public class Graph {
    private final Map<Integer, Point> points = new HashMap<>();
    private final Map<Integer, Edge> edges = new HashMap<>();
    private final Map<Integer, List<Edge>> adjMap = new HashMap<>();

    public void registerPoint(int id, int val) {
        points.put(id, new Point(id, val));
    }

    public void registerLink(int id, int src, int dst, int cost) {
        Edge link = new Edge(id, src, dst, cost);
        edges.put(id, link);
        adjMap.computeIfAbsent(src, k -> new ArrayList<>()).add(link);
    }

    public Point getPoint(int id) { return points.get(id); }
    public List<Edge> getOutbound(int id) {
        return adjMap.getOrDefault(id, Collections.emptyList());
    }

    public void showInternalState() {
        System.out.println("--- Graph Report ---");
        points.values().forEach(p -> System.out.println(" P: " + p));
        edges.values().forEach(c -> System.out.println(" L: " + c.getUid() + " [" + c.getSource() + "->" + c.getTarget() + "]"));
    }
}