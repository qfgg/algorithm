import java.util.*;


public class Main {
    public static int minimumDeletions(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        if (len == 0) {
            return 0;
        }
        int bDel = 0, unbalanceA = 0, unbalanceB = 0, b = 0;
        for (int i = 0; i < len; i++) {
            if (chars[i] == 'b') {
                b++;
            } else {
                if (unbalanceA > 0) {
                    unbalanceA++;
                } else if (b > 0) {
                    unbalanceB += b;
                    b = 0;
                    unbalanceA++;
                }
            }
            if (unbalanceB <= unbalanceA) {
                bDel += unbalanceB;
                unbalanceB = 0;
                unbalanceA = 0;
            }
        }
        return bDel + unbalanceA;
    }
    public static void main(String[] args) {
        String s = "bbbbbbbaabbbbbaaabbbabbbbaabbbbbbaabbaaabaabbbaaaabaaababbbabbabbaaaabbbabbbbbaabbababbbaaaaaababaaababaabbabbbaaaabbbbbabbabaaaabbbaba";
        int ans = minimumDeletions(s);
        System.out.println(ans);
    }
}