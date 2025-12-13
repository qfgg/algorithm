import java.util.*;


class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length, i, diff, minsum = Integer.MAX_VALUE;
        int[] presum = new int[n];
        HashMap<Integer, Integer> sum2idx = new HashMap<>();
        List<int[]> subs = new ArrayList<>();
        for (i = 0; i < n; i++) {
            presum[i] = i == 0 ? arr[i] : presum[i - 1] + arr[i];
            diff = presum[i] - target;
            if (diff == 0) {
                subs.add(new int[]{0, i});
            } else if (sum2idx.containsKey(diff)) {
                subs.add(new int[]{sum2idx.get(diff) + 1, i});
            }
            sum2idx.put(presum[i], i);
        }
        n = subs.size();
        if (n < 2) {
            return -1;
        }
        int lmin = Integer.MAX_VALUE, rmin = Integer.MAX_VALUE;
        int[] cur, lmap = new int[n], rmap = new int[n];
        int[] ends = new int[100000], starts = new int[100000];
        Arrays.fill(ends, -1);
        Arrays.fill(starts, -1);
        for (i = 0; i < n; i++) {
            cur = subs.get(i);
            ends[cur[1]] = i;
            starts[cur[0]] = i;
            lmin = Math.min(lmin, cur[1] - cur[0] + 1);
            lmap[i] = lmin;
        }
        for (i = n - 1; i >= 0; i--) {
            cur = subs.get(i);
            rmin = Math.min(rmin, cur[1] - cur[0] + 1);
            rmap[i] = rmin;
        }
        int l = -1;
        for (i = 0; i < 100000; i++) {
             if (starts[i] != -1 && l != -1) {
                 minsum = Math.min(minsum, lmap[l] + rmap[starts[i]]);
                 l = -1;
             }
             if (ends[i] != -1) {
                 l = ends[i];
             }
        }
        return minsum == Integer.MAX_VALUE ?  -1 : minsum;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[]{3,2,2,4,3};
        int target = 3;
        int res = s.minSumOfLengths(arr, target);
        System.out.println(res);
    }
}
