import java.util.*;

class Solution {
    public int add1(int[] q, int[] nums, int i, int h, int t) {
        while (h < t && nums[i] >= nums[q[t - 1]]) {
            t--;
        }
        q[t] = i;
        return t + 1;
    }

    public int add2(int[] q, int[] nums, int i, int h, int t) {
        while (h < t && nums[i] <= nums[q[t - 1]]) {
            t--;
        }
        q[t] = i;
        return t + 1;
    }
    public int longestSubarray(int[] nums, int limit) {
        int i = 0, j = 1, n = nums.length, h1 = 0, t1 = 1, h2 = 0, t2 = 1, l;
        int[] q1 = new int[n], q2 = new int[n];
        q1[0] = 0;
        q2[0] = 0;
        l = 1;
        while (j < n) {
            t1 = add1(q1, nums, j, h1, t1);
            t2 = add2(q2, nums, j, h2, t2);
            if (nums[q1[h1]] - nums[q2[h2]] <= limit) {
                if (j - i + 1 > l) {
                    l = j - i + 1;
                }
                j++;
            } else {
                if (q1[h1] == i) {
                    h1++;
                }
                if (q2[h2] == i) {
                    h2++;
                }
                i++;
            }
        }
        return l;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{8,2,4,7};
        int limit = 4;
        int res = s.longestSubarray(nums, limit);
        System.out.println(res);
    }
}
