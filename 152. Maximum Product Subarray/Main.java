import java.util.*;


class Solution {
    public int maxProductNoZero(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int i = start, j = end, lp = 1, rp = 1, mp = 1, k;
        while (i <= end && nums[i] > 0) {
            lp *= nums[i];
            i++;
        }
        if (i > end) {
            return lp;
        }
        while (j > i && nums[j] > 0) {
            rp *= nums[j];
            j--;
        }
        if (i == j) {
            return Math.max(lp, rp);
        }
        for (k = i + 1; k <= j - 1; k++) {
            mp *= nums[k];
        }
        if (mp > 0) {
            return lp * nums[i] * mp * nums[j] * rp;
        }
        return Math.max(Math.max(lp, mp * nums[j] * rp), Math.max(lp * nums[i] * mp, rp));
    }
    public int maxProduct(int[] nums) {
        int n = nums.length, i, max = Integer.MIN_VALUE, start = 0;
        for (i = 0; i < n; i++) {
            if (nums[i] == 0) {
                if (i > start) {
                    max = Math.max(0, max);
                    max = Math.max(maxProductNoZero(nums, start, i - 1), max);
                } else {
                    max = Math.max(max, 0);
                }
                start = i + 1;
            } else if (i == n -1) {
                max = Math.max(maxProductNoZero(nums, start, i), max);
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();
        int[] nums = new int[]{-4,-3,-2};
        int res = sl.maxProduct(nums);
        System.out.println(res);
    }
}
