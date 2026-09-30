import java.util.*;


class Solution {
    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray(), w2 = word2.toCharArray();
        int n1 = w1.length, n2 = w2.length, i, j;
        int[][] dp = new int[n1][n2];
        for (i = 0; i < n1; i++) {
            for (j = 0; j < n2; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (w1[i] == w2[j]) {
                    dp[i][j]++;
                }
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
                if (j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
            }
        }
        return n1 + n2 - 2 * dp[n1 - 1][n2 - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        String word1 = "leetcode", word2 = "etco";
        int ret = slt.minDistance(word1, word2);
        System.out.println(ret);
    }
}
