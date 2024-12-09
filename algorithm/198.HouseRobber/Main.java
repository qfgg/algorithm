import java.util.*;

class Solution {
    // f[i]: the max value to rob from 0 - i, if nums[i] taken
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int i;
        int[] f = new int[n];
        f[0] = nums[0];
        for (i = 1; i < n; i++) {
            f[i] = Math.max(
                    i >= 2 ? f[i - 2] + nums[i] : nums[i],
                    i >= 3 ? f[i - 3] + nums[i] : nums[i]
            );
        }
        return Math.max(f[n - 1], f[n - 2]);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{0};
        int res = s.rob(nums);
        System.out.println(res);
    }
}
