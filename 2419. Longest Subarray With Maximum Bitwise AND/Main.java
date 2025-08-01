import java.util.*;


class Solution {
    public int longestSubarray(int[] nums) {
        int i, n = nums.length, cnt = 0, maxLen = 1, maxVal = 0;
        for (i = 0; i < n; i++) {
            maxVal = Math.max(maxVal, nums[i]);
        }
        for (i = 0; i < n; i++) {
            if (nums[i] == maxVal) {
                cnt++;
                maxLen = Math.max(maxLen, cnt);
            } else {
                cnt = 0;
            }
        }
        return maxLen;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{311155,311155,311155,311155,311155,311155,311155,311155,201191,311155};
        int res = s.longestSubarray(nums);
        System.out.println(res);
    }
}
