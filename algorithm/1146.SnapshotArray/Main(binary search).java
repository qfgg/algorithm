import java.util.*;

class SnapshotArray {
    private Map<Integer, List<int[]>> snapValue;
    private Map<Integer, Integer> updates;
    private int curSid;
    public SnapshotArray(int length) {
        snapValue = new HashMap<>();
        updates = new HashMap<>();
        curSid = 0;
    }
    public void set(int index, int val) {
        updates.put(index, val);
    }
    public int snap() {
        for (int key : updates.keySet()) {
            if (!snapValue.containsKey(key)) {
                snapValue.put(key, new ArrayList<>());
            }
            snapValue.get(key).add(new int[]{curSid, updates.get(key)});
        }
        updates.clear();
        curSid++;
        return curSid - 1;
    }
    public int get(int index, int snap_id) {
        List<int[]> list = snapValue.get(index);
        if (list == null) {
            return 0;
        }
        int[] cur;
        int n = list.size(), l = 0, r = n, m;
        while (l < r) {
            m = l + ((r - l) >> 1);
            cur = list.get(m);
            if (cur[0] == snap_id) {
                return cur[1];
            }
            if (cur[0] < snap_id) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        if (l == n) {
            l--;
        }
        if (list.get(l)[0] <= snap_id) {
            return list.get(l)[1];
        }
        return l == 0 ? 0 : list.get(l - 1)[1];
    }
}

public class Main {
    public static void main(String[] args) {
        SnapshotArray snapshotArr = new SnapshotArray(3);
        snapshotArr.set(1,3);
        snapshotArr.set(1,8);
        System.out.println(snapshotArr.snap());
        System.out.println(snapshotArr.get(1,0));
        snapshotArr.set(2,5);
        System.out.println(snapshotArr.get(2,0));
        System.out.println(snapshotArr.snap());
        System.out.println(snapshotArr.snap());
        snapshotArr.set(0,6);
        snapshotArr.set(0,12);
        System.out.println(snapshotArr.get(0,1));
        System.out.println(snapshotArr.snap());
        snapshotArr.set(2,18);
        snapshotArr.set(2,20);
        snapshotArr.set(0,1);
        System.out.println(snapshotArr.get(2,1));
        System.out.println(snapshotArr.snap());
        System.out.println(snapshotArr.get(2,2));
    }
}
