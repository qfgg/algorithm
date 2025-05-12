import java.util.*;


class Solution {
    private long countOne(int[] nums, int idx, int lower, int upper) {
        int n = nums.length, l = idx + 1, r = n, m, lIdx = n, rIdx = -1;
        while (l < r) {
            m = (l + r) / 2;
            if (nums[m] < lower) {
                l = m + 1;
            } else {
                lIdx = Math.min(lIdx, m);
                r = m;
            }
        }
        if (lIdx == n && l != 0) {
            return 0;
        }
        if (lIdx != n) {
            l = lIdx;
        }
        r = nums.length;
        while (l < r) {
            m = (l + r) / 2;
            if (nums[m] <= upper) {
                rIdx = Math.max(rIdx, m);
                l = m + 1;
            } else {
                r = m;
            }
        }
        return rIdx < lIdx ? 0 : rIdx - lIdx + 1;
    }
    public long countFairPairs(int[] nums, int lower, int upper) {
        long count = 0;
        Arrays.sort(nums);
        int i, n = nums.length;
        for (i = 0; i < n; i++) {
            count += countOne(nums, i, lower - nums[i], upper - nums[i]);
        }
        return count;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{0,0,0,0,0,0};
        int lower = 0, upper = 0;
        long res = s.countFairPairs(nums, lower, upper);
        System.out.println(res);
    }
}
