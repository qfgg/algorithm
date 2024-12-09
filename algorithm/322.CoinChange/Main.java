import java.util.Arrays;

public class Main {
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int num = coins.length, i, j;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (i = 0; i < num; i++) {
            for (j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != -1) {
                    if (dp[j] == -1) {
                        dp[j] = 1 + dp[j - coins[i]];
                    } else {
                        dp[j] = Math.min(dp[j], 1 + dp[j - coins[i]]);
                    }
                }
            }
        }
        return dp[amount];
    }
    public static void main(String[] args) {
        int[] coins = new int[]{2};
        int amount = 3;
        int ans = coinChange(coins, amount);
        System.out.println(ans);
    }
}
