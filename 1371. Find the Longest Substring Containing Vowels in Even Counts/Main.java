import java.util.*;


class Solution {
    int getIdx(int lastIdx, char c) {
        if (c == 'a') {
            return lastIdx ^ 1;
        }
        if (c == 'e') {
            return lastIdx ^ (1 << 1);
        }
        if (c == 'i') {
            return lastIdx ^ (1 << 2);
        }
        if (c == 'o') {
            return lastIdx ^ (1 << 3);
        }
        if (c == 'u') {
            return lastIdx ^ (1 << 4);
        }
        return lastIdx;
    }
    public int findTheLongestSubstring(String s) {
        char[] ch = s.toCharArray();
        int n = ch.length, i, lastIdx = 0, maxlen = 0;
        int[] len = new int[64];
        for (i = 0; i < n; i++) {
            lastIdx = getIdx(lastIdx, ch[i]);
            if (lastIdx == 0) {
                maxlen = Math.max(maxlen, i + 1);
            } else {
                if (len[lastIdx] == 0) {
                    len[lastIdx] = i + 1;
                } else {
                    maxlen = Math.max(maxlen, i + 1 - len[lastIdx]);
                }
            }
        }
        return maxlen;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "eleetminicoworoep";
        int res = s.findTheLongestSubstring(str);
        System.out.println(res);
    }
}
