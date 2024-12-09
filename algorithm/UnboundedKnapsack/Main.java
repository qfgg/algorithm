import java.util.*;

public class Main {
    public static int unboundedKnapsack(int W, int N, int[] w, int[] v) {
        int[] dp = new int[W + 1];
        for (int i = 0; i < N; i++) {
            for (int j = w[i]; j <= W; j++) {
                if (v[i] + dp[j - w[i]] > dp[j]) {
                    dp[j] = v[i] + dp[j - w[i]];
                }
            }
        }
        return dp[W];
    }
    public static void main(String[] args) {
        int N = 4, W = 10;
        int[] w = new int[]{2,3,4,7};
        int[] v = new int[]{1,3,5,9};
        int ans = unboundedKnapsack(W, N, w, v);
        System.out.println(ans);
    }
}
