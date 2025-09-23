import java.util.*;


class Solution {
    boolean canCatchup(int left, int right, int target, int[] position, int[] speed) {
        double leftTime = (double)(target - position[left]) / (double)speed[left];
        double rightTime = (double)(target - position[right]) / (double)speed[right];
        return leftTime <= rightTime;
    }
    void AddOne(int target, int[] position, int[] speed, int pos, int n, List<Integer> fleets) {
        if (fleets.isEmpty()) {
            fleets.add(pos);
            return;
        }
        int l = 0, r = fleets.size(), m, mpos, leftmost = n;
        while (l < r) {
            m = (l + r) / 2;
            mpos = fleets.get(m);
            if (canCatchup(mpos, pos, target, position, speed)) {
                leftmost = Math.min(leftmost, m);
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (leftmost < n) {
            fleets.subList(leftmost, fleets.size()).clear();
        }
        fleets.add(pos);
    }
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length, i;
        Integer[] idx = new Integer[n];
        for (i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (a, b) -> position[a] - position[b]);
        List<Integer> fleets = new ArrayList<>();
        for (i = 0; i < n; i++) {
            AddOne(target, position, speed, idx[i], n, fleets);
        }
        return fleets.size();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int target = 10;
        int[] position = new int[]{6,8}, speed = new int[]{3,2};
        int res = s.carFleet(target, position, speed);
        System.out.println(res);
    }
}
