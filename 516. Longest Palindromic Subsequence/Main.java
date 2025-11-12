import java.util.*;


class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s.length() == 1) {
            return 1;
        }
        char[] ch = s.toCharArray();
        int n = ch.length, i, j;
        int[] dp1 = new int[n], dp2 = new int[n], next = new int[n], tmp;
        for (i = 0; i < n; i++) {
            dp1[i] = 1;
        }
        for (i = 1; i < n; i++) {
            dp2[i] = ch[i - 1] == ch[i] ? 2 : 1;
        }
        for (j = 2; j < n; j++) {
            for (i = j; i < n; i++) {
                next[i] = Math.max(dp2[i - 1], dp2[i]);
                next[i] = Math.max(next[i], ch[i] == ch[i - j] ? dp1[i - 1] + 2 : dp1[i - 1]);
            }
            tmp = dp1;
            dp1 = dp2;
            dp2 = next;
            next = tmp;
        }
        return dp2[n - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "a";
        int res = s.longestPalindromeSubseq(str);
        System.out.println(res);
    }
}
