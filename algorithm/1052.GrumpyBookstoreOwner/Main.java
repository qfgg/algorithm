import java.util.*;

class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int l = 0, r = 0, n = customers.length, sum = 0, i, max = 0, cur = 0;
        for (i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                sum += customers[i];
            }
        }
        while (r < n) {
            if (r - l + 1 > minutes) {
                if (grumpy[l] == 1) {
                    cur -= customers[l];
                }
                l++;
            }
            if (grumpy[r] == 1) {
                cur += customers[r];
            }
            if (cur > max) {
                max = cur;
            }
            r++;
        }
        return sum + max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] customers = new int[]{1,0,1,2,1,1,7,5}, grumpy = new int[]{0,1,0,1,0,1,0,1};
        int minutes = 3;
        int res = s.maxSatisfied(customers, grumpy, minutes);
        System.out.println(res);
    }
}
