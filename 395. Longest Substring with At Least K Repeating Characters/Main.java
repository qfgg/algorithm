import java.util.*;


class Solution {
    int find (char[] c, int start, int end, int k, int[][] count) {
        HashSet<Integer> less = new HashSet<>();
        int j, scount, ecount, maxlen = 0;
        for (j = 0; j < 26; j++) {
            if (count[end][j] > 0) {
                scount = start > 0 ? count[start - 1][j] : 0;
                ecount = count[end][j];
                if (ecount - scount > 0 && ecount - scount < k) {
                    less.add(j);
                }
            }
        }
        if (less.isEmpty()) {
            return end - start + 1;
        }
        int left = start;
        for (j = start; j <= end; j++) {
            if (less.contains(c[j] - 'a')) {
                if (j - left >= k) {
                    maxlen = Math.max(maxlen, find(c, left, j - 1, k, count));
                }
                left = j + 1;
            }
        }
        if (end - left + 1 >= k) {
            maxlen = Math.max(maxlen, find(c, left, end, k, count));
        }
        return maxlen;
    }
    public int longestSubstring(String s, int k) {
        char[] c = s.toCharArray();
        int n = c.length, i, j;
        int[][] count = new int[n][26];
        count[0][c[0] - 'a']++;
        for (i = 1; i < n; i++) {
            for (j = 0; j < 26; j++) {
                count[i][j] = count[i - 1][j];
            }
            count[i][c[i] - 'a']++;
        }
        return find(c, 0, n - 1, k, count);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "bbaaacbd";
        int k = 3;
        int res = s.longestSubstring(str, k);
        System.out.println(res);
    }
}
