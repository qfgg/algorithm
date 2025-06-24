import java.util.*;


class Solution {
    boolean checkPrime(int n) {
        if (n == 2) {
            return true;
        }
        int sqrt = (int)Math.ceil(Math.sqrt(n)), i;
        for (i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    int getNum(List<Integer> digits) {
        int num = 0, i, n = digits.size();
        for (i = n - 1; i >= 0; i--) {
            num = num * 10 + digits.get(i);
        }
        return num;
    }
    void getNextPalindrome(List<Integer> digits) {
        int n = digits.size(), half = (n - 1) / 2, i, origin, j;
        for (i = half; i >= 0; i--) {
            origin = digits.get(i);
            if (origin < 9) {
                digits.set(i, origin + 1);
                digits.set(n - 1 - i, origin + 1);
                for (j = i + 1; j < n - 1 - i; j++) {
                    digits.set(j, 0);
                }
                return;
            }
        }
        digits.add(1);
        digits.set(0, 1);
        for (i = 1; i < n; i++) {
            digits.set(i, 0);
        }
    }
    public int primePalindrome(int n) {
        if (n == 1) {
            return 2;
        }
        if (n < 10 && checkPrime(n)) {
            return n;
        }
        List<Integer> digits = new ArrayList<>(List.of(n % 10));
        int cur = n / 10, count = 0, half, i;
        while (cur > 0) {
            digits.add(cur % 10);
            count++;
            cur = cur / 10;
        }
        List<Integer> copy = new ArrayList<>(digits);
        half = count / 2;
        for (i = 0; i <= half; i++) {
            copy.set(i, copy.get(count - i));
        }
        int next = getNum(copy);
        while (next < n || !checkPrime(next)) {
            getNextPalindrome(copy);
            next = getNum(copy);
        }
        return next;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 2;
        int res = s.primePalindrome(n);
        System.out.println(res);
    }
}
