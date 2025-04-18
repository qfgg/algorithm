import java.util.*;

class Solution {
    public int maxWidthRamp(int[] nums) {
        int len = nums.length, i;
        List<Integer> idx = new ArrayList<>();
        for (i = 0; i < len; i++) {
            idx.add(i);
        }
        Collections.sort(idx, (a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);
        int[] min = new int[len];
        int max = 0, maxWidth = 0;
        min[0] = idx.get(0);
        for (i = 1; i < len; i++) {
            min[i] = Math.min(min[i - 1], idx.get(i));
        }
        for (i = len - 1; i >= 0; i--) {
            max = Math.max(max, idx.get(i));
            maxWidth = Math.max(maxWidth, max - min[i]);
        }
        return maxWidth;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{9,8,1,0,1,9,4,0,4,1};
        int res = s.maxWidthRamp(nums);
        System.out.println(res);
    }
}
