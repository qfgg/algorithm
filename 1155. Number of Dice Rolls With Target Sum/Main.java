import java.util.*;


class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        if (target < n || target > n * k) {
            return 0;
        }
        if (k > target) {
            k = target;
        }
        int[][] dp = new int[n][target + 1];
        int i, j, q;
        int mod = 1000000007;
        for (j = 1; j <= k; j++) {
            dp[0][j] = 1;
        }
        for (i = 1; i < n; i++) {
            for (j = 1; j <= target; j++) {
                for (q = 1; q <= k; q++) {
                    if (j > q) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - q]) % mod;
                    }
                }
            }
        }
        return dp[n - 1][target];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 1, k = 6, target = 3;
        int res = s.numRollsToTarget(n, k, target);
        System.out.println(res);
    }
}
