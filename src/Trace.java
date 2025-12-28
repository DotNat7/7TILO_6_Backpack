public class Trace {
    public int time, linkId, fee, pointId, pointVal, remBudget, totalZ;

    public Trace(int t, Integer lid, int f, int pid, int pv, int rb, int tz) {
        this.time = t;
        this.linkId = (lid == null) ? -1 : lid;
        this.fee = f;
        this.pointId = pid;
        this.pointVal = pv;
        this.remBudget = rb;
        this.totalZ = tz;
    }

    @Override
    public String toString() {
        if (linkId == -1)
            return String.format("[t_%d] u_%d (%d) -> r=%d, z=%d", time, pointId, pointVal, remBudget, totalZ);
        return String.format("[t_%d] h_%d (%d), u_%d (%d) -> r=%d, z=%d", time, linkId, fee, pointId, pointVal, remBudget, totalZ);
    }
}