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
        int[][] dp = new int[n1 + 1][n2 + 1];
        dp[0][0] = 3;
        for (i = 0; i <= n1; i++) {
            for (j = 0; j <= n2; j++) {
                if (j > 0 && dp[i][j - 1] > 0) {
                    if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                        dp[i][j] = 2;
                    }
                }
                if (i > 0 && dp[i - 1][j] > 0) {
                    if (s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                        dp[i][j] |= 1;
                    }
                }
            }
        }
        return dp[n1][n2] > 0;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc";
        boolean res = s.isInterleave(s1, s2, s3);
        System.out.println(res);
    }
}
