import java.util.*;


class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length, i, j, sum = 0;
        for (i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum = sum / 2;
        int[][] dp = new int[n][sum + 1];
        for (j = 0; j <= sum; j++) {
            if (j >= nums[0]) {
                dp[0][j] =nums[0];
            }
        }
        for (i = 1; i < n; i++) {
            for (j = 0; j <= sum; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], nums[i] + dp[i - 1][j - nums[i]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                if (dp[i][j] == sum) {
                    return true;
                }
            }
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,5,11,5};
        boolean res = s.canPartition(nums);
        System.out.println(res);
    }
}
