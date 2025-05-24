import java.util.*;


class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        char[] ch1 = text1.toCharArray(), ch2 = text2.toCharArray();
        int n1 = ch1.length, n2 = ch2.length, i, j;
        int[][] len = new int[n2][n1];
        for (j = 0; j < n1; j++) {
            if (ch2[0] == ch1[j]) {
                len[0][j] = 1;
            } else if (j > 0 && len[0][j - 1] == 1) {
                len[0][j] = len[0][j - 1];
            }
        }
        for (i = 0; i < n2; i++) {
            if (ch1[0] == ch2[i]) {
                len[i][0] = 1;
            } else if (i > 0 && len[i - 1][0] == 1) {
                len[i][0] = len[i - 1][0];
            }
        }
        for (i = 1; i < n2; i++) {
            for (j = 1; j < n1; j++) {
                if (ch1[j] == ch2[i]) {
                    len[i][j] = len[i - 1][j - 1] + 1;
                } else {
                    len[i][j] = Math.max(Math.max(len[i - 1][j - 1], len[i - 1][j]), len[i][j - 1]);
                }
            }
        }
        return len[n2 - 1][n1 - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String t1 = "babbaa";
        String t2 = "ababa";
        int res = s.longestCommonSubsequence(t1, t2);
        System.out.println(res);
    }
}
