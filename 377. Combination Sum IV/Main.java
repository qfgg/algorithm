import java.util.*;


class Solution {
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length, i, j, k, sum = 0;
        int[][] dp = new int[target + 1][n]; // dp[t][j]: number of combinations of sum t starting with item j
        for (k = 0; k < n; k++) {
            if (nums[k] <= target) {
                dp[nums[k]][k] = 1;
            }
        }
        for (i = 1; i <= target; i ++) {
            for (j = 0; j < n; j++) {
                for (k = 0; k < n; k++) {
                    if (i > nums[j]) {
                        dp[i][j] += dp[i - nums[j]][k];
                    }
                }
            }
        }
        for (k = 0; k < n; k++) {
            sum += dp[target][k];
        }
        return sum;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,2,3};
        int target = 4;
        long res = s.combinationSum4(nums, target);
        System.out.println(res);
    }
}
