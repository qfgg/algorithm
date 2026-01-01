import java.util.*;


class Solution {
    public int findNthDigit(int n) {
        int i;
        long remain = n, last = n;
        for (i = 1; i < 10; i++) {
            remain -= 9 * (long)Math.pow(10, i - 1) * i;
            if (remain < 0) {
                break;
            }
            last = remain;
        }
        long k = last / i, mod = last % i, target, m = i - mod;
        if (mod == 0) {
            target = (int)Math.pow(10, i - 1) + k - 1;
            return (int)target % 10;
        }
        target = (int)Math.pow(10, i - 1) + k;
        while (m > 0) {
            target = target / 10;
            m--;
        }
        return (int)target % 10;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 1000000000;
        int res = s.findNthDigit(n);
        System.out.println(res);
    }
}
//12345678910111213141516