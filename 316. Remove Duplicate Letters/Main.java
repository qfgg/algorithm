import java.util.*;


class Solution {
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        if (len == 1) {
            return s;
        }
        char[] ch = s.toCharArray();
        int i, cnt = 0, key, exclude = (1 << 26) - 1, curBits, nextBits, cur;
        boolean[] occur = new boolean[26];
        int[] suffixBits = new int[len];
        key = ch[len - 1] - 'a';
        suffixBits[len - 1] = 1 << key;
        occur[key] = true;
        cnt++;
        for (i = len - 2; i >= 0; i--) {
            key = ch[i] - 'a';
            suffixBits[i] = suffixBits[i + 1] | (1 << key);
            if (!occur[key]) {
                occur[key] = true;
                cnt++;
            }
        }
        StringBuilder sb = new StringBuilder();
        cur = 0;
        curBits = suffixBits[0];
        i = 1;
        while (i < len) {
            if (!occur[ch[i] - 'a']) {
                i++;
                continue;
            }
            nextBits = suffixBits[i] & exclude;
            if (nextBits != curBits) {
                key = ch[cur] - 'a';
                sb.append(ch[cur]);
                occur[key] = false;
                cnt--;
                if (cnt == 0) {
                    break;
                }
                exclude &= ~(1 << key);
                cur++;
                while (cur < len && !occur[ch[cur] - 'a']) {
                    cur++;
                }
                if (cur < len) {
                    curBits = suffixBits[cur] & exclude;
                }
                i = cur + 1;
            } else {
                if (ch[i] < ch[cur]) {
                    cur = i;
                }
                i++;
            }
        }
        if (cnt > 0) {
            sb.append(ch[cur]);
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "bddbccd";
        String res = s.removeDuplicateLetters(str);
        System.out.println(res);
    }
}
