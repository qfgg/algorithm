public class Main {
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int min = coins[0];
        for (int coin : coins) {
            if (coin < min) {
                min = coin;
            }
        }
        if (min > amount) {
            return -1;
        }
        int num = coins.length, i, j, n, cur, pre, globalMin;
        int[] dp = new int[amount + 1];
        for (j = 1; j <= amount; j++) {
            dp[j] = j % coins[0] == 0 ? j / coins[0] : -1;
        }
        globalMin = dp[amount];
        for (i = 1; i < num; i++) {
            for (j = amount; j >= 1; j--) {
                n = j / coins[i];
                min = -1;
                if (globalMin != -1 && globalMin <= n) {
                    n = globalMin - 1;
                }
                while (n >= 0) {
                    pre = dp[j - n * coins[i]];
                    if (pre != -1 && (globalMin == -1 || pre < globalMin)) {
                        cur = n + pre;
                        if (min == - 1 || cur < min) {
                            min = cur;
                        }
                    }
                    n--;
                }
                dp[j] = min;
                if (j == amount && (globalMin == -1 || (min != -1 && min < globalMin))) {
                    globalMin = min;
                }
            }
        }
        return globalMin;
    }
    public static void main(String[] args) {
        int[] coins = new int[]{1,2,5};
        int amount = 11;
        int ans = coinChange(coins, amount);
        System.out.println(ans);
    }
}
