import java.util.*;


class Solution {
    public int getMaxLen(int[] nums) {
        int i, n = nums.length, firstNeg = -1, negCnt = 0, maxLen = 0, start = -1;
        for (i = 0; i < n; i++) {
            if (nums[i] == 0) {
                firstNeg = -1;
                negCnt = 0;
                start = i;
            } else if (nums[i] < 0) {
                if (firstNeg == -1) {
                    firstNeg = i;
                }
                negCnt++;
            }
            if (negCnt % 2 == 0) {
                maxLen = Math.max(maxLen, i - start);
            } else if (firstNeg != -1) {
                maxLen = Math.max(maxLen, i - firstNeg);
            }
        }
        return maxLen;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{0,1,-2,-3,-4};
        int res = s.getMaxLen(nums);
        System.out.println(res);
    }
}
