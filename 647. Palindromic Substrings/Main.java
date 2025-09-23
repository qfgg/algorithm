import java.util.*;


class Solution {
    public int countSubstrings(String s) {
        char[] ch = s.toCharArray();
        int n = ch.length, i, j, cnt = 0;
        boolean[][] isP = new boolean[n][n];
        for (i = 0; i < n; i = i + 2) {
            for (j = 0; j < n - i; j++) {
                if (i == 0 || (isP[j + 1][j + i - 1] && ch[j] == ch[j + i])) {
                    isP[j][j + i] = true;
                    cnt++;
                }
            }
        }
        for (i = 1; i < n; i = i + 2) {
            for (j = 0; j < n - i; j++) {
                if ((i == 1 || isP[j + 1][j + i - 1]) && ch[j] == ch[j + i]) {
                    isP[j][j + i] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "aaa";
        int res = s.countSubstrings(str);
        System.out.println(res);
    }
}
