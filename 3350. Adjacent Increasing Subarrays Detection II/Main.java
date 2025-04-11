import java.util.*;

class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int max = 1, len = nums.size(), i, prevEnd;
        int[] l = new int[len];
        l[0] = 1;
        for (i = 1; i < len; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                l[i] = l[i - 1] + 1;
            } else {
                l[i] = 1;
            }
            if (l[i] > 1) {
                max = Math.max(max, l[i] / 2);
            }
        }
        for (i = len - 1; i >= 0; i--) {
            prevEnd = i - l[i];
            if (prevEnd >= 0) {
                max = Math.max(max, Math.min(l[prevEnd], l[i]));
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Integer> nums = new ArrayList<>(Arrays.asList(-15,-13,4,7));
        int res = s.maxIncreasingSubarrays(nums);
        System.out.println(res);
    }
}
