import java.util.*;


class Solution {
    public int minSteps(String s, String t) {
        char[] sc = s.toCharArray(), tc = t.toCharArray();
        int n = sc.length, common = 0, i, idx;
        int[] cnt = new int[26];
        for (i = 0; i < n; i++) {
            cnt[sc[i] - 'a']++;
        }
        for (i = 0; i < n; i++) {
            idx = tc[i] - 'a';
            if (cnt[idx] > 0) {
                cnt[idx]--;
                common++;
            }
        }
        return n - common;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "leetcode", t = "practice";
        int res = s.minSteps(str, t);
        System.out.println(res);
    }
}
