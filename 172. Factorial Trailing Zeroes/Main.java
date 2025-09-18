import java.util.*;


class Solution {
    public int trailingZeroes(int n) {
        int n5 = 0, mul5 = 1, res = 0, pre = 0, cur;
        while (mul5 <= n) {
            mul5 *= 5;
            n5++;
        }
        n5--;
        mul5 /= 5;
        while (mul5 >= 5) {
            cur = n / mul5;
            res += (cur - pre) * n5;
            pre = cur;
            n5--;
            mul5 /= 5;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.trailingZeroes(200);
        System.out.println(res);
    }
}
