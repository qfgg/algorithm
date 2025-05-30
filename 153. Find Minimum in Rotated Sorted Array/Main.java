import java.util.*;


class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length, m;
        while (l < r) {
            m = (l + r) / 2;
            if (m > 0 && nums[m - 1] > nums[m]) {
                return nums[m];
            }
            if (nums[m] < nums[0]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return nums[0];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{3,4,5,1,2};
        int res = s.findMin(nums);
        System.out.println(res);
    }
}
