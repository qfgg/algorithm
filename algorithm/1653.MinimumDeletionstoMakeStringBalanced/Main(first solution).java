import java.util.*;


public class Main {
    public static int minimumDeletions(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        int an = 0, i, min, leftan = 0, leftbn = 0, cur;
        boolean seenB = false, noop = true;
        char[] chars = s.toCharArray();
        for (i = 0; i < len ; i++) {
            if (chars[i] == 'a') {
                an++;
                if (seenB) {
                    noop = false;
                }
            } else {
                seenB = true;
            }
        }
        if (noop) {
            return 0;
        }
//        result is in form of aaa...bbb..., so compute left part num of b and right part num of a
//        edge case: if left part is empty, which means whole string is right part, so
//        delete all a
        min = an;
        for (i = 0; i < len ; i++) {
            if (chars[i] == 'a') {
                leftan++;
            } else {
                leftbn++;
            }
            cur = leftbn + an - leftan;
            if (cur < min) {
                min = cur;
            }
        }
        return min;
    }
    public static void main(String[] args) {
        String s = "bbbbbbbaabbbbbaaabbbabbbbaabbbbbbaabbaaabaabbbaaaabaaababbbabbabbaaaabbbabbbbbaabbababbbaaaaaababaaababaabbabbbaaaabbbbbabbabaaaabbbaba";
        int ans = minimumDeletions(s);
        System.out.println(ans);
    }
}
