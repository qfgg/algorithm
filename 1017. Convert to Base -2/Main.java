import java.util.*;


class Solution {
    void compute(long n, char[] ch) {
        if (n == 0) {
            return;
        }
        long s0, pow;
        int k, max = ch.length - 1;
        if (n > 0) {
            k = 0;
            s0 = 1;
            pow = 1;
            while (n > s0) {
                pow = pow * 4;
                s0 = (pow * 4 - 1) / 3;
                k++;
            }
            ch[max - 2 * k] = '1';
        } else {
            k = 1;
            s0 = -2;
            pow = -2;
            while (n < s0) {
                pow = pow * 4;
                s0 = (pow * 4 + 2) / 3;
                k++;
            }
            ch[max - 2 * k + 1] = '1';
        }
        compute(n - pow, ch);
    }
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        long s0 = 1, pow = 1;
        int k = 0;
        while (n > s0) {
            pow = pow * 4;
            s0 = (pow * 4 - 1) / 3;
            k++;
        }
        char[] ch = new char[2 * k + 1];
        Arrays.fill(ch, '0');
        ch[0] = '1';
        compute(n - pow, ch);
        return new String(ch);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 419887699;
        String res = s.baseNeg2(n);
        System.out.println(res);
    }
}
