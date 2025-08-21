import java.util.*;


class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length();
        if (s3.length() != n1 + n2) {
            return false;
        }
        if (n1 == 0) {
            return s2.equals(s3);
        }
        if (n2 == 0) {
            return s1.equals(s3);
        }
        int i, j;
        boolean[] dp = new boolean[n2 + 1];
        dp[0] = true;
        for (j = 1; j <= n2; j++) {
            dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        for (i = 1; i <= n1; i++) {
            for (j = 0; j <= n2; j++) {
                dp[j] = (j > 0 && dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) ||
                        (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[n2];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String s1 = "db", s2 = "b", s3 = "cbb";
        boolean res = s.isInterleave(s1, s2, s3);
        System.out.println(res);
    }
}
