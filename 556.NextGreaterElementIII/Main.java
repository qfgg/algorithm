import java.util.*;


public class Main {
    public static int nextGreaterElement(int n) {
        if (n >= 1 && n <= 11) {
            return -1;
        }
        List<Integer> digits = new ArrayList<>();
        int tmp = n, cur = 0, d = 0, pre = 0;
        while (tmp > 0) {
            d = tmp % 10;
            digits.add(d);
            if (cur > 0 && d < digits.get(cur - 1)) {
                pre = cur - 1;
                while (pre - 1 >= 0 && d < digits.get(pre - 1)) {
                    pre--;
                }
                break;
            }
            tmp = tmp / 10;
            cur++;
        }
        if (tmp == 0) {
            return -1;
        }
        tmp = tmp - tmp % 10 + digits.get(pre);
        digits.remove(pre);
        Collections.sort(digits);
        while (digits.size() > 1) {
            tmp = tmp * 10 + digits.get(0);
            digits.remove(0);
        }
        d = digits.get(0);
        if (tmp > (Integer.MAX_VALUE - d) / 10) {
            return -1;
        }
        return tmp * 10 + d;
    }
    public static void main(String[] args) {
        int n = 999999;
        int ans = nextGreaterElement(n);
        System.out.println(ans);
    }
}
