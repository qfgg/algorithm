import java.util.*;


class Solution {
    int findMin(int[] nums, int k, int pos) {
        if (pos == nums.length - 1) {
            if (nums[pos] >= k) {
                return 1;
            }
            return Integer.MAX_VALUE;
        }
        int i, n = nums.length, cur = 0, border, min;
        for (i = pos; i < n; i++) {
            cur = cur | nums[i];
            if (cur >= k) {
                break;
            }
        }
        if (i == n) {
            return Integer.MAX_VALUE;
        }
        border = i;
        cur = 0;
        for (i = border; i >= 0; i--) {
            cur = cur | nums[i];
            if (cur >= k) {
                break;
            }
        }
        min = border - i + 1;
        if (border < n - 1) {
            min = Math.min(min, findMin(nums, k, i + 1));
        }
        return min;
    }
    public int minimumSubarrayLength(int[] nums, int k) {
        int ret = findMin(nums, k, 0);
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{32,1,2,81,76,58};
        int res = s.minimumSubarrayLength(nums, 125);
        System.out.println(res);
    }
}
