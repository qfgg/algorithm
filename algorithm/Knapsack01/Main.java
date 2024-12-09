public class Main {
    public static int knapsack01(int W, int N, int[] w, int[] v) {
        int[] dp = new int[W + 1];
        for (int i = 0; i < N; i++) {
            for (int j = W; j >= w[i]; j--) {
                if (v[i] + dp[j - w[i]] > dp[j]) {
                    dp[j] = v[i] + dp[j - w[i]];
                }
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {
        int W = 8;
        int n = 5;
        int[] w = {3, 5, 1, 2, 2};
        int[] v = {4, 5, 2, 1, 3};
        int ret = knapsack01(W, n, w, v);
        System.out.println(ret);
    }
}
