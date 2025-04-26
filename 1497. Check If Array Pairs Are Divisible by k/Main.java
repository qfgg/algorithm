import java.util.*;


class Solution {
    public boolean canArrange(int[] arr, int k) {
        int[] remainder = new int[k];
        int remainderCount = 0, i, n = arr.length, r;
        for (i = 0; i < n; i++) {
            r = arr[i] % k;
            if (r < 0) {
                r += k;
            }
            if (remainder[(k - r) % k] > 0) {
                remainder[(k - r) % k]--;
                remainderCount--;
            } else {
                remainder[r]++;
                remainderCount++;
            }
        }
        return remainderCount == 0;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{-4,-7,5,2,9,1,10,4,-8,-3};
        boolean res = s.canArrange(nums, 3);
        System.out.println(res);
    }
}
