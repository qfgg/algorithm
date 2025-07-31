import java.util.*;


class Solution {
    int getOnePart(char[] ch, int pos) {
        boolean[] occur = new boolean[26];
        int n = ch.length, i = pos, curIdx;
        while (i < n) {
            curIdx = ch[i] - 'a';
            if (occur[curIdx]) {
                break;
            }
            occur[curIdx] = true;
            i++;
        }
        return i;
    }
    public int partitionString(String s) {
        char[] ch = s.toCharArray();
        int next, n = ch.length, cnt = 0;
        next = getOnePart(ch, 0);
        cnt++;
        while (next < n) {
            next = getOnePart(ch, next);
            cnt++;
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "abacaba";
        int res = s.partitionString(str);
        System.out.println(res);
    }
}
