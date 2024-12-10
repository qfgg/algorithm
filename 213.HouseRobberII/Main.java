import java.util.*;

class Solution {
    // f[i]: the max value to rob from 0 - i, if nums[i] taken
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int i;
        int[] f1 = new int[n];
        f1[0] = nums[0];
        for (i = 1; i < n - 1; i++) {
            f1[i] = Math.max(
                    i >= 2 ? f1[i - 2] + nums[i] : nums[i],
                    i >= 3 ? f1[i - 3] + nums[i] : nums[i]
            );
        }
        int[] f2 = new int[n];
        f2[0] = nums[1];
        for (i = 2; i < n; i++) {
            f2[i - 1] = Math.max(
                    i >= 3 ? f2[i - 3] + nums[i] : nums[i],
                    i >= 4 ? f2[i - 4] + nums[i] : nums[i]
            );
        }
        return Math.max(
                Math.max(f1[n - 2], n >= 3 ? f1[n - 3] : 0),
                Math.max(f2[n - 2], n >= 3 ? f2[n - 3] : 0)
        );
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,3,1,3,100};
        int res = s.rob(nums);
        System.out.println(res);
    }
}
