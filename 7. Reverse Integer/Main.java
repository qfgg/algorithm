import java.util.*;


class Solution {
    List<Integer> getDigits(int x, boolean isNeg) {
        List<Integer> digits = new ArrayList<>();
        int d = isNeg ? -10 : 10, cur;
        while (x != 0) {
            cur = isNeg ? -(x % d) : x % d;
            digits.add(cur);
            x = x / 10;
        }
        return digits;
    }
    int getNum(List<Integer> digits) {
        int res = 0, len = digits.size(), i;
        for (i = 0; i < len; i++) {
            res = res * 10 + digits.get(i);
        }
        return res;
    }
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        boolean isNeg = x < 0;
        List<Integer> digits = getDigits(x, isNeg);
        int i, len = digits.size();
        if (len == 10) {
            List<Integer> maxDigits;
            if (isNeg) {
                maxDigits = getDigits(Integer.MIN_VALUE, true);
            } else {
                maxDigits = getDigits(Integer.MAX_VALUE, false);
            }
            for (i = 0; i < 10; i++) {
                if (digits.get(i) < maxDigits.get(9 - i)) {
                    break;
                }
                if (digits.get(i) > maxDigits.get(9 - i)) {
                    return 0;
                }
            }
        }
        int res = getNum(digits);
        return isNeg ? -res : res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int x = -2147483412;
        int res = s.reverse(x);
        System.out.println(res);
    }
}
