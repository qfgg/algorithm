import java.util.*;


class Solution {
    public int findNumberOfLIS(int[] nums) {
        int i, j, n = nums.length, maxLen = 0, cnt = 0, len;
        int[] maxLens = new int[n], lenCounts = new int[n];
        for (i = n - 1; i >= 0; i--) {
            maxLens[i] = 1;
            lenCounts[i] = 1;
            for (j = i + 1; j < n; j++) {
                if (nums[i] < nums[j]) {
                    len = maxLens[j] + 1;
                    if (len > maxLens[i]) {
                        maxLens[i] = len;
                        lenCounts[i] = lenCounts[j];
                    } else if (len == maxLens[i]) {
                        lenCounts[i] += lenCounts[j];
                    }
                }
            }
            if (maxLens[i] > maxLen) {
                maxLen = maxLens[i];
                cnt = lenCounts[i];
            } else if (maxLens[i] == maxLen) {
                cnt += lenCounts[i];
            }
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{3,1,2};
        int res = s.findNumberOfLIS(nums);
        System.out.println(res);
    }
}
