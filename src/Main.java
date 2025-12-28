import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Naplnění daty
        int[][] pts = {{1,1},{3,3},{4,4},{5,5},{10,10},{12,12},{14,14},{15,15},{19,19},{20,20},{36,36},{40,40},{43,43},{101,1}, {105,5}};
        for(int[] p : pts) graph.registerPoint(p[0], p[1]);

        int[][] lks = {
                {1, 1, 40, 4}, {2, 40, 1, 4}, {3, 40, 1, 30}, {4, 40, 4, 78}, {5, 3, 40, 30},
                {6, 4, 40, 78}, {7, 4, 19, 57}, {8, 4, 20, 30}, {9, 19, 4, 57}, {10, 20, 4, 30},
                {11, 5, 40, 145}, {12, 5, 14, 132}, {13, 5, 101, 193}, {14, 14, 5, 132},
                {15, 14, 15, 21}, {16, 14, 10, 48}, {17, 15, 14, 21}, {18, 10, 14, 48},
                {19, 101, 5, 193}, {20, 101, 12, 150}, {21, 5, 101, 193}, {22, 12, 101, 150},
                {23, 12, 43, 23}, {24, 12, 36, 27}, {25, 43, 12, 23}, {26, 36, 12, 27}
        };
        for(int[] l : lks) graph.registerLink(l[0], l[1], l[2], l[3]);

        System.out.println("r = 455\nz = 0\n");

        PathFinder engine = new PathFinder(graph);
        List<Trace> logs = engine.process(5, 455);

        logs.forEach(System.out::println);

        if(!logs.isEmpty()) {
            Trace last = logs.get(logs.size()-1);
            System.out.println("\nNavštívených uzlů: " + logs.size());
            System.out.println("Finální z = " + last.totalZ);
            System.out.println("Zbývající r = " + last.remBudget);
        }
    }
}