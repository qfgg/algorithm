import java.util.*;


class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == divisor) {
            return 1;
        }
        if (dividend + divisor == 0) {
            return -1;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (divisor == -1) {
            return -dividend;
        }
        boolean isNeg = false;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            isNeg = true;
        }
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;
        int q = 0;
        while (dividend <= divisor) {
            dividend -= divisor;
            q++;
        }
        return isNeg ? -q : q;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.divide(-1010369383, -2147483648);
        System.out.println(res);
    }
}
