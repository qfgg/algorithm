import java.util.*;


class Solution {
    boolean isPrime(int n) {
        if (n == 2) {
            return true;
        }
        int max = (int)Math.sqrt(n);
        for (int i = 2; i <= max; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    int operate(int n, int lower) {
        int next = lower + 1;
        while (n - next > 1 && !isPrime(n - next)) {
            next++;
        }
        return n - next <= 1 ? n : next;
    }
    public boolean primeSubOperation(int[] nums) {
        int pre = 0, i, n = nums.length, after;
        for (i = 0; i < n; i++) {
            after = operate(nums[i], pre);
            if ((i > 0 && after <= pre) || (i < n - 1 && after >= nums[i + 1])) {
                return false;
            }
            pre = after;
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{5,8,3};
        boolean res = s.primeSubOperation(nums);
        System.out.println(res);
    }
}
