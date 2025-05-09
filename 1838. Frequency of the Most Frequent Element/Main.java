import java.util.*;


class Solution {
    private int searchOne(int[] nums, int idx, int k, int[] presum) {
        int high = idx, low = 0, mid, max = 1, n;
        while (low < high) {
            mid = (high + low) / 2;
            n = idx - mid;
            if ((mid == 0 && nums[idx] * n - presum[idx - 1] <= k) ||
                    (mid > 0 && nums[idx] * n - (presum[idx - 1] - presum[mid - 1]) <= k)) {
                max = Math.max(max, n + 1);
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return max;
    }
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length, i, max = 1;
        if (n == 1) {
            return max;
        }
        Arrays.sort(nums);
        int[] presum = new int[n];
        presum[0] = nums[0];
        for (i = 1; i < n; i++) {
            presum[i] = presum[i - 1] + nums[i];
        }
        for (i = 1; i < n; i++) {
            max = Math.max(max, searchOne(nums, i, k, presum));
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,4,8,13};
        int k = 5;
        int res = s.maxFrequency(nums, k);
        System.out.println(res);
    }
}
