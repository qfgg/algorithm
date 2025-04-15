import java.util.*;

class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        int i, k = 0, r;
        boolean negative = false;
        if (n == Integer.MIN_VALUE) {
            negative = true;
            k = 32;
        } else {
            if (n < 0) {
                n = -n;
                negative = true;
            }
            r = n;
            while (r > 0) {
                r = r / 2;
                k++;
            }
        }

        double res = 1;
        double[] pow = new double[k + 1];
        pow[0] = 1;
        pow[1] = x;
        for (i = 2; i <= k; i++) {
            pow[i] = pow[i - 1] * pow[i - 1];
        }
        for (i = 0; i <= k; i++) {
            if ((n & (1 << i)) != 0) {
                res = res * pow[i + 1];
            }
        }
        return negative ? 1 / res : res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        double x = 2;
        int n = -2;
        double res = s.myPow(x, n);
        System.out.println(res);
    }
}
