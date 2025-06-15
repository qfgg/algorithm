import java.util.*;


class Solution {
    boolean countRange(int[] nums, int start, int end, int[] res) {
        int n = nums.length, i;
        int maxWithin = end - start + 1, count = 0;
        for (i = 0; i < n; i++) {
            if (nums[i] >= start && nums[i] <= end) {
                count++;
                if (count > maxWithin) {
                    return true;
                }
            }
        }
        return false;
    }
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int m, lower, upper;
        int[] res = new int[1];
        lower = 1;
        upper = n - 1;
        while (lower < upper) {
            m = (lower + upper) / 2;
            if (countRange(nums, lower, m, res)) {
                if (res[0] > 0) {
                    return res[0];
                }
                upper = m;
            } else {
                lower = m + 1;
            }
        }
        return lower;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{8,1,1,1,2,7,4,3,1,1};
        int res = s.findDuplicate(nums);
        System.out.println(res);
    }
}
