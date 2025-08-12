import java.util.*;


class Solution {
    int searchLeft(int k, int[][] cnt, int right) {
        int l = 0, r = right + 1, m, left = 0, i;
        while (l < r) {
            m = (l + r) / 2;
            for (i = 0; i < 26; i++) {
                if (cnt[right][i] - cnt[m][i] >= k) {
                    break;
                }
            }
            if (i == 26) {
                r = m;
            } else {
                left = m + 1;
                l = m + 1;
            }
        }
        return left;
    }
    public int numberOfSubstrings(String s, int k) {
        char[] ch = s.toCharArray();
        int len = ch.length, i, j, pos, total = 0, left, minright = len;
        int[][] cnt = new int[len][26];
        for (i = 0; i < len; i++) {
            if (i > 0) {
                for (j = 0; j < 26; j++) {
                    cnt[i][j] = cnt[i - 1][j];
                }
            }
            pos = ch[i] - 'a';
            cnt[i][pos]++;
            if (cnt[i][pos] == k && minright == len) {
                minright = i;
            }
            if (i >= minright) {
                left = searchLeft(k, cnt, i);
                total += left + 1;
            }
        }
        return total;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "abacb";
        int k = 2;
        int res = s.numberOfSubstrings(str, k);
        System.out.println(res);
    }
}
