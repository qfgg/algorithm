import java.util.*;


class Solution {
    public String smallestBeautifulString(String s, int k) {
        char[] sc = s.toCharArray();
        int sl = sc.length, i;
        boolean[] dp = new boolean[sl];
        boolean[] carry = new boolean[sl];
        char[] dpc = new char[sl];
        int[] digit = new int[sl];
        for (i = 0; i < sl; i++) {
            digit[i] = sc[i] - 'a';
        }
        int nt;
        char next;
        boolean nextCarry;
        // if the highest digit is the largest one, not possible to grow
        if (digit[0] < k - 1) {
            dp[0] = true;
            carry[0] = false;
            dpc[0] = (char)(sc[0] + 1);
        }
        for (i = 1; i < sl; i++) {
            nt = digit[i] + 1;
            next = (char)(nt % k + 'a');
            nextCarry = nt == k;
            nt = nt % k;
            while (true) {
                if (nextCarry) {
                    if (!dp[i - 1]) {
                        break;
                    }
                    if (next == dpc[i - 1] ||
                            (carry[i - 1] && i > 1 && next == dpc[i - 2]) ||
                            (!carry[i - 1] && i > 1 && next == sc[i - 2])) {
                        nt++;
                        next = (char)(nt % k + 'a');
                        nt = nt % k;
                    } else {
                        dp[i] = true;
                        carry[i] = true;
                        dpc[i] = next;
                        break;
                    }
                } else {
                    if (next == sc[i - 1] || (i > 1 && next == sc[i - 2])) {
                        nt++;
                        next = (char)(nt % k + 'a');
                        nextCarry = nt == k;
                        nt = nt % k;
                    } else {
                        dp[i] = true;
                        carry[i] = false;
                        dpc[i] = next;
                        break;
                    }
                }
            }
        }
        if (!dp[sl - 1]) {
            return "";
        }
        for (i = sl - 1; i >= 0; i--) {
            sc[i] = dpc[i];
            if (!carry[i]) {
                break;
            }
        }
        return new String(sc);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "abdc";
        int k = 4;
        String res = s.smallestBeautifulString(str, k);
        System.out.println(res);
    }
}
