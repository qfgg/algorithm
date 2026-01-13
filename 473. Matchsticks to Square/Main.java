import java.util.*;


class Solution {
    public boolean makesquare(int[] matchsticks) {
        int n = matchsticks.length, i, k, sum = 0, l, bit, pre;
        for (i = 0; i < n; i++) {
            sum += matchsticks[i];
        }
        if (sum % 4 != 0) {
            return false;
        }
        l = sum / 4;
        int max = 1 << n;
        int[] dp = new int[max];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (i = 1; i < max; i++) {
            for (k = 0; k < n; k++) {
                bit = 1 << k;
                if ((i & bit) > 0) {
                    pre = dp[i ^ bit];
                    if (dp[i] == -1 && pre != -1 && pre + matchsticks[k] <= l) {
                        dp[i] = (pre + matchsticks[k]) % l;
                    }
                }
            }
        }
        return dp[max - 1] == 0;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] matchsticks = new int[]{1,1,2,2,2};
        boolean res = s.makesquare(matchsticks);
        System.out.println(res);
    }
}
