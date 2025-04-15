import java.util.*;

class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length, m = 0;
        while (l < r) {
            m = (l + r) / 2;
            if ((m == 0 || nums[m - 1] < nums[m]) &&
                    (m == nums.length - 1 || nums[m] > nums[m + 1])) {
                return m;
            }
            if ((m == 0 || nums[m - 1] < nums[m]) &&
                    (m < nums.length - 1 && nums[m] <= nums[m + 1])) {
                l = m + 1;
            } else if ((m > 0 && nums[m - 1] >= nums[m]) &&
                    (m == nums.length - 1 || nums[m] > nums[m + 1])) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = { 1,3,2,1 };
        int res = s.findPeakElement(nums);
        System.out.println(res);
    }
}
