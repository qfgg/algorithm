import java.util.*;

class Solution {
    public int numSplits(String s) {
        int n = s.length(), i, cnt = 0;
        Set<Character> charSet = new HashSet<>();
        int[] pre = new int[n];
        int[] suf = new int[n];
        for (i = 0; i < n - 1; i++) {
            charSet.add(s.charAt(i));
            pre[i] = charSet.size();
        }
        charSet.clear();
        for (i = n - 1; i > 0; i--) {
            charSet.add(s.charAt(i));
            suf[i] = charSet.size();
        }
        for (i = 0; i < n - 1; i++) {
            if (pre[i] == suf[i + 1]) {
                cnt++;
            }
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution st = new Solution();
        String s = "aacaba";
        int res = st.numSplits(s);
        System.out.println(res);
    }
}
