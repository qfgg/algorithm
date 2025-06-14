import java.util.*;


class Solution {
    public boolean checkValidString(String s) {
        char[] ch = s.toCharArray();
        int n = ch.length, i, j, k;
        boolean[][] dp = new boolean[n][n];
        for (j = 0; j < n; j++) {
            dp[j][j] = ch[j] == '*';
        }
        for (j = 0; j < n - 1; j++) {
            dp[j][j + 1] = (ch[j] == '(' || ch[j] == '*') && (ch[j + 1] == ')' || ch[j + 1] == '*');
        }
        for (i = 2; i < n; i++) {
            for (j = 0; j + i < n; j++) {
                dp[j][j + i] = (ch[j] == '(' || ch[j] == '*') && (ch[j + i] == ')' || ch[j + i] == '*') && dp[j + 1][j + i - 1];
                if (!dp[j][j + i]) {
                    for (k = j; k < j + i; k++) {
                        dp[j][j + i] = dp[j][k] && dp[k + 1][j + i];
                        if (dp[j][j + i]) {
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
//        String str = "(*()*((()*)";
        String str = "(*))";
        boolean res = s.checkValidString(str);
        System.out.println(res);
    }
}
