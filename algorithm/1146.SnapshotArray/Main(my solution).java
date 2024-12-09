import java.util.*;

class SnapshotArray {
    private List<Map<Integer, Integer>> logs;
    private int curId;
    public SnapshotArray(int length) {
        logs = new ArrayList<>();
        logs.add(new HashMap<>());
        curId = 0;
    }
    public void set(int index, int val) {
        logs.get(curId).put(index, val);
    }
    public int snap() {
        logs.add(new HashMap<>());
        curId++;
        return curId - 1;
    }
    public int get(int index, int snap_id) {
        int i = snap_id;
        Map<Integer, Integer> updates;
        while (i >= 0) {
            updates = logs.get(i);
            if (updates.containsKey(index)) {
                return updates.get(index);
            }
            i--;
        }
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
        snapshotArr.set(0,5);  // Set array[0] = 5
        System.out.println(snapshotArr.snap());  // Take a snapshot, return snap_id = 0
        snapshotArr.set(0,6);
        System.out.println(snapshotArr.get(0,0));  // Get the value of array[0] with snap_id = 0, return 5
    }
}
