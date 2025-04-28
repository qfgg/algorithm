import java.util.*;


class Solution {
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        int i, cn = coins.length, k;
        int[][] dp = new int[amount + 1][cn];
        for (k = 0; k < cn; k++) {
            dp[0][k] = 1;
        }
        for (i = 1; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[i][0] = 1;
            }
        }
        for (i = 1; i <= amount; i++) {
            for (k = 1; k < cn; k++) {
                if (i >= coins[k]) {
                    dp[i][k] = dp[i][k - 1] + dp[i - coins[k]][k];
                } else {
                    dp[i][k] = dp[i][k - 1];
                }
            }
        }
        return dp[amount][cn - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int amount = 5;
        int[] coins = new int[]{1,2,5};
        int res = s.change(amount, coins);
        System.out.println(res);
    }
}
