import java.util.*;


class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, i;
        int[] diff = new int[n];
        for (i = 0; i < n; i++) {
            diff[i] = gas[i] - cost[i];
        }
        int[] presum = new int[n];
        presum[0] = diff[0];
        boolean hasNeg = presum[0] < 0;
        for (i = 1; i < n; i++) {
            presum[i] = presum[i - 1] + diff[i];
            if (presum[i] < 0) {
                hasNeg = true;
            }
        }
        if (!hasNeg) {
            return 0;
        }
        if (presum[n - 1] < 0) {
            return -1;
        }
        int[] lmin = new int[n];
        int[] rmin = new int[n];
        lmin[1] = presum[0];
        for (i = 2; i < n; i++) {
            lmin[i] = Math.min(lmin[i - 1], presum[i - 1]);
        }
        rmin[n - 1] = presum[n - 1];
        for (i = n - 2; i >= 0; i--) {
            rmin[i] = Math.min(rmin[i + 1], presum[i]);
        }
        for (i = 1; i < n; i++) {
            if (lmin[i] + presum[n - 1] - presum[i - 1] >= 0 && rmin[i] - presum[i - 1] >= 0) {
                return i;
            }
        }
        return -1;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        int[] gas = new int[]{4};
        int[] cost = new int[]{5};
        int res = slt.canCompleteCircuit(gas, cost);
        System.out.println(res);
    }
}
