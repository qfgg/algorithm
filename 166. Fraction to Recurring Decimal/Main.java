import java.util.*;


class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        long n = numerator, d = denominator;
        StringBuilder sb = new StringBuilder(), frac = new StringBuilder();
        if ((n < 0 && d > 0) || (n > 0 && d < 0)) {
            sb.append('-');
        }
        n = Math.abs(n);
        d = Math.abs(d);
        sb.append(n / d);
        long remain = n % d;
        HashMap<Long, Integer> r = new HashMap<>();
        if (remain == 0) {
            return sb.toString();
        }
        sb.append('.');
        r.put(remain, 0);
        while (true) {
            remain = remain * 10;
            frac.append(remain / d);
            remain = remain % d;
            if (remain == 0 || r.containsKey(remain)) {
                break;
            }
            r.put(remain, frac.length());
        }
        if (remain == 0) {
            sb.append(frac);
        } else {
            frac.insert((int)r.get(remain), '(');
            sb.append(frac);
            sb.append(')');
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int numerator = 1, denominator = 214748364;
        String res = s.fractionToDecimal(numerator, denominator);
        System.out.print(res);
    }
}
