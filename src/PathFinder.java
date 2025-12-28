import java.util.*;

public class PathFinder {
    private final Graph graph;
    private final Set<Integer> seenPoints = new HashSet<>();
    private final Set<Integer> activeLinks = new HashSet<>();
    private final List<Trace> history = new ArrayList<>();

    private double wallet;
    private int scoreZ;
    private int tick = 1;

    public PathFinder(Graph graph) {
        this.graph = graph;
    }

    public List<Trace> process(int startId, int budget) {
        Point start = graph.getPoint(startId);
        if (start == null) return history;

        this.wallet = budget;
        this.scoreZ = start.getValue();
        this.seenPoints.add(startId);

        // První záznam
        history.add(new Trace(tick++, null, 0, startId, start.getValue(), (int)wallet, scoreZ));

        PriorityQueue<Edge> queue = new PriorityQueue<>((o1, o2) ->
                Double.compare(calculateRank(o2), calculateRank(o1)));

        refreshQueue(queue, startId);

        while (!queue.isEmpty()) {
            Edge bestLink = queue.poll();
            int dest = bestLink.getTarget();

            if (seenPoints.contains(dest)) continue;

            int price = activeLinks.contains(bestLink.getUid()) ? 0 : bestLink.getWeight();

            // Zachování specifické podmínky původního kódu (cost == 4)
            if (wallet < price || price == 4) continue;

            // Provedení kroku
            wallet -= price;
            activeLinks.add(bestLink.getUid());
            Point p = graph.getPoint(dest);
            seenPoints.add(dest);
            scoreZ += p.getValue();

            history.add(new Trace(tick++, bestLink.getUid(), price, dest, p.getValue(), (int)wallet, scoreZ));
            refreshQueue(queue, dest);
        }
        return history;
    }

    private double calculateRank(Edge c) {
        Point target = graph.getPoint(c.getTarget());
        int potential = target.getValue();
        if (potential <= 0) return -1.0;

        int fee = activeLinks.contains(c.getUid()) ? 0 : c.getWeight();
        return (fee == 0) ? Double.MAX_VALUE : (double) potential / fee;
    }

    private void refreshQueue(PriorityQueue<Edge> q, int pId) {
        for (Edge c : graph.getOutbound(pId)) {
            if (!seenPoints.contains(c.getTarget())) q.add(c);
        }
    }
}