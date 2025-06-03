import java.util.*;


class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length, i;
        int[][] dp = new int[n][2];
        dp[0][1] = -prices[0];
        for (i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][1], i > 1 ? dp[i - 2][0] - prices[i] : - prices[i]);
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] prices = new int[]{1,2,3,0,2};
        int res = s.maxProfit(prices);
        System.out.println(res);
    }
}
