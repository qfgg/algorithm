import java.util.*;


class Solution {
    public int triangleNumber(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int i, n = nums.length, cnt = 0, l, r;
        for (i = n - 1; i >= 2; i--) {
            l = 0;
            r = i - 1;
            while (l < r) {
                while (l < r && nums[l] + nums[r] > nums[i]) {
                    cnt += r - l;
                    r--;
                }
                while (l < r && nums[l] + nums[r] <= nums[i]) {
                    l++;
                }
            }
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{4,2,3,4};
        int res = s.triangleNumber(nums);
        System.out.println(res);
    }
}
