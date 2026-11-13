import java.util.*;


class Solution {
    public int minimumAverageDifference(int[] nums) {
        int i, n = nums.length, min, next, idx;
        long[] presum = new long[n];
        presum[0] = nums[0];
        for (i = 1; i < n; i++) {
            presum[i] = presum[i - 1] + nums[i];
        }
        min = (int)(presum[n - 1] / n);
        idx = n - 1;
        for (i = 0; i < n - 1; i++) {
            next = (int)(Math.abs((presum[n - 1] - presum[i]) / (n - i - 1) - presum[i] / (i + 1)));
            if (next < min || (next == min && i < idx)) {
                min = next;
                idx = i;
            }
        }
        return idx;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        int[] nums = new int[]{2,5,3,9,5,3};
        int ret = slt.minimumAverageDifference(nums);
        System.out.println(ret);
    }
}
