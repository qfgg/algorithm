import java.util.*;

class Solution {
    public int maximumSwap(int num) {
        if (num < 10) {
            return num;
        }
        int copy = num, i = 0, res = 0;
        List<Integer> digits = new ArrayList<>();
        while (copy > 0) {
            digits.add(copy % 10);
            copy = copy / 10;
            i++;
        }
        int len = digits.size(), l = len - 1, r = len - 1;
        for (i = len - 1; i > 0; i--) {
            if (digits.get(i) < digits.get(i - 1)) {
                l = i;
                r = i - 1;
                break;
            }
        }
        if (i == 0) {
            return num;
        }
        i = r - 1;
        while (i >= 0) {
            if (digits.get(i) >= digits.get(r)) {
                r = i;
            }
            i--;
        }
        i = l + 1;
        while (i < len) {
            if (digits.get(i) < digits.get(r)) {
                l = i;
            }
            i++;
        }
        for (i = len - 1; i >= 0; i--) {
            if (i < len - 1) {
                res *= 10;
            }
            res += digits.get(i == l ? r : i == r ? l : i);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.maximumSwap(9874245);
        System.out.println(res);
    }
}
