import java.util.*;


class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        if (word1.isEmpty()) {
            return word2.length();
        }
        if (word2.isEmpty()) {
            return word1.length();
        }
        char[] c1 = word1.toCharArray(), c2 = word2.toCharArray();
        int n1 = word1.length(), n2 = word2.length(), i, j;
        int[][] dp = new int[n1][n2];
        for (i = 0; i < n1; i++) {
            for (j = 0; j < n2; j++) {
                if (c1[i] == c2[j]) {
                    if (i == 0 && j == 0) {
                        dp[i][j] = 0;
                    } else if (i > 0 && j > 0) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {
                    if (i == 0 && j == 0) {
                        dp[i][j] = 1;
                    } else if (i > 0 && j > 0) {
                        dp[i][j] = Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1);
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j - 1] + 1;
                    } else {
                        dp[i][j] = dp[i - 1][j] + 1;
                    }
                }
            }
        }
        return dp[n1 - 1][n2 - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String word1 = "intention", word2 = "execution";
        int res = s.minDistance(word1, word2);
        System.out.println(res);
    }
}
