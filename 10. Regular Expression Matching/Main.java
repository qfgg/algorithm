import java.util.*;


class Solution {
    private int preprocess(String s, String p, char[] sc, char[] pc, boolean[] flag) {
        int n = p.length(), i, j = 0;
        char cur;
        for (i = 0; i < n; i++) {
            cur = p.charAt(i);
            if (cur == '*') {
                flag[j - 1] = true;
            } else {
                pc[j] = cur;
                j++;
            }
        }
        sc[0] = 0;
        n = s.length();
        for (i = 0; i < n; i++) {
            sc[i + 1] = s.charAt(i);
        }
        return j;
    }
    public boolean isMatch(String s, String p) {
        char[] sc = new char[s.length() + 1];
        char[] pc = new char[p.length()];
        boolean[] flag = new boolean[pc.length];
        int pl = preprocess(s, p, sc, pc, flag);
        int i, j, sl = sc.length;
        boolean[][] dp = new boolean[pl][sl];
        dp[0][0] = flag[0];
        for (i = 1; i < sl; i++) {
            if (flag[0]) {
                dp[0][i] = (pc[0] == sc[i] || pc[0] == '.') && dp[0][i - 1];
            } else {
                dp[0][i] = i == 1 && (pc[0] == sc[i] || pc[0] == '.');
            }
        }
        for (j = 1; j < pl; j++) {
            if (flag[j]) {
                dp[j][0] = dp[j - 1][0];
            } else {
                dp[j][0] = false;
            }
        }
         for (i = 1; i < sl; i++) {
            for (j = 1; j < pl; j++) {
                if (flag[j]) {
                    dp[j][i] = dp[j - 1][i] || (dp[j][i - 1] && (sc[i] == pc[j] || pc[j] == '.'));
                } else {
                    dp[j][i] = dp[j - 1][i - 1] && (sc[i] == pc[j] || pc[j] == '.');
                }
            }
        }
        return dp[j - 1][i - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "aa";
        String p = "a*";
        boolean res = s.isMatch(str, p);
        System.out.println(res);
    }
}
