import java.util.*;


class Solution {
    public int getSum(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        int sum = a ^ b, carry = (a & b) << 1;
        return getSum(sum, carry);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int a = -1, b = 1;
        int res = s.getSum(a, b);
        System.out.println(res);
    }
}
