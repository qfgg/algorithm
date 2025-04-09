import java.util.*;

class Solution {
    public int splitArray(int[] nums, int k) {
        int len = nums.length, i, j, l, suffixSum = 0;
        int[][] dp = new int[k][len];
        dp[0][0] = nums[0];
        for (i = 1; i < len; i++) {
            dp[0][i] = dp[0][i - 1] + nums[i];
        }
        for (i = 1; i < k; i++) {
            for (j = len - 1; j >= i; j--) {
                for (l = j; l >= i; l--) {
                    if (l == j) {
                        suffixSum = nums[l];
                        dp[i][j] = Integer.MAX_VALUE;
                    } else {
                        suffixSum = suffixSum + nums[l];
                    }
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][l - 1], suffixSum));
                }
            }
        }
        return dp[k - 1][len - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = { 7,2,5,10,8 };
        int k = 3;
        int res = s.splitArray(nums, k);
        System.out.println(res);
    }
}
