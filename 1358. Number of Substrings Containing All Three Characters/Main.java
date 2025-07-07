import java.util.*;


class Solution {
    public int numberOfSubstrings(String s) {
        char[] ch = s.toCharArray();
        int[] cnt = new int[3];
        int n = ch.length, l = 0, r = 0, cat = 0, total = 0, ri, li;
        while (l <= r && r < n) {
            ri = ch[r] - 'a';
            cnt[ri]++;
            if (cnt[ri] == 1) {
                cat++;
            }
            if (cat == 3) {
                while (l <= r && cat == 3) {
                    total += n - r;
                    li = ch[l] - 'a';
                    cnt[li]--;
                    if (cnt[li] == 0) {
                        cat--;
                    }
                    l++;
                }
            }
            r++;
        }
        return total;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "abcabc";
        int res = s.numberOfSubstrings(str);
        System.out.println(res);
    }
}
