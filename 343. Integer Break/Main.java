import java.util.*;


class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int i, j;
        for (i = 2; i <= n; i++) {
            for (j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], j * (i - j));
                dp[i] = Math.max(dp[i], dp[j] * (i - j));
            }
        }
        return dp[n];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 10;
        int res = s.integerBreak(n);
        System.out.println(res);
    }
}
