import java.util.*;


class Solution {
    long count(int[] digits, int[] target, int n) {
        long[][] dp = new long[n][3];
        int i, j;
        for (j = 0; j < n; j++) {
            if (target[0] == digits[j]) {
                dp[j][0] = j > 0 ? dp[j - 1][0] + 1 : 1;
            } else {
                dp[j][0] = j > 0 ? dp[j - 1][0] : 0;
            }
        }
        for (i = 1; i < 3; i++) {
            for (j = 1; j < n; j++) {
                if (target[i] == digits[j]) {
                    dp[j][i] = dp[j - 1][i] + dp[j - 1][i - 1];
                } else {
                    dp[j][i] = dp[j - 1][i];
                }
            }
        }
        return dp[n - 1][2];
    }
    public long numberOfWays(String s) {
        int n = s.length(), i;
        int[] digits = new int[n], target1 = new int[]{1,0,1}, target2 = new int[]{0,1,0};
        for (i = 0; i < n; i++) {
            digits[i] = s.charAt(i) - '0';
        }
        return count(digits, target1, n) + count(digits, target2, n);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "001101";
        long res = s.numberOfWays(str);
        System.out.println(res);
    }
}
