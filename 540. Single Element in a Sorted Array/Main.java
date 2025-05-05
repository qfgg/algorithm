import java.util.*;


class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length, l = 0, r = n, m;
        while (l < r) {
            m = (l + r) / 2;
            if ((m == 0 && nums[0] != nums[1]) ||
                    (m == n - 1 && nums[n - 1] != nums[n - 2]) ||
                    (nums[m - 1] != nums[m] && nums[m] != nums[m + 1])) {
                return nums[m];
            }
            if (m % 2 == 1) {
                if (nums[m - 1] != nums[m]) {
                    r = m;
                } else {
                    l = m + 1;
                }
            } else {
                if (nums[m + 1] != nums[m]) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
        }
        return l;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1};
        int res = s.singleNonDuplicate(nums);
        System.out.println(res);
    }
}
