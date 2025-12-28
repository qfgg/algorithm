import java.util.*;


class Solution {
    boolean isValid(int n) {
        List<Integer> d = new ArrayList<>();
        int copy = n;
        while (copy > 0) {
            d.addFirst(copy % 10);
            copy = copy / 10;
        }
        int len = d.size(), i, cur;
        boolean hasR = false;
        for (i = 0; i < len; i++) {
            cur = d.get(i);
            if (cur == 3 || cur == 4 || cur == 7) {
                return false;
            }
            if (cur == 2 || cur == 5 || cur == 6 || cur == 9) {
                hasR = true;
            }
        }
        return hasR;
    }
    public int rotatedDigits(int n) {
        int total = 0;
        for (int i = 0; i <= n; i++) {
            if (isValid(i)) {
                total++;
            }
        }
        return total;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 9;
        int res = s.rotatedDigits(n);
        System.out.println(res);
    }
}
