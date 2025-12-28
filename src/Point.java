public class Point {
    private final int id;
    private final int value;
    private boolean isReached = false;

    public Point(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public int getId() { return id; }
    public int getValue() { return value; }
    public boolean isReached() { return isReached; }
    public void setReached(boolean reached) { isReached = reached; }

    @Override
    public String toString() {
        return "Point{id=" + id + ", val=" + value + ", visited=" + isReached + "}";
    }
}