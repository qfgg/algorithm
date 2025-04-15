import java.util.*;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }
        int l = 0, r = nums.length, m, left = -1, right = -1;
        while (l < r) {
            m = (l + r) / 2;
            if (nums[m] == target && (m == 0 || nums[m - 1] < target)) {
                left = m;
                break;
            }
            if (nums[m] >= target) {
                r = m;
            } else if (nums[m] < target) {
                l = m + 1;
            }
        }
        if (l == r) {
            left = -1;
        }
        l = Math.max(l, left);
        r = nums.length;
        while (l < r) {
            m = (l + r) / 2;
            if (nums[m] == target && (m == nums.length - 1 || nums[m + 1] > target)) {
                right = m;
                break;
            }
            if (nums[m] <= target) {
                l = m + 1;
            } else if (nums[m] > target) {
                r = m;
            }
        }
        return new int[] {left, right};
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{2,2};
        int[] res = s.searchRange(nums, 2);
        System.out.println(Arrays.toString(res));
    }
}
