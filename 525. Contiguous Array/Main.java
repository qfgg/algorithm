import java.util.*;


class Solution {
    public int findMaxLength(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
//        count (number of 1s) - (number of 0s) mapping to index
        HashMap<Integer, Integer> countIdx = new HashMap<>();
        countIdx.put(0, -1);
        int i, max = 0, cur = 0;
        for (i = 0; i < len; i++) {
            cur = nums[i] == 1 ? cur + 1 : cur - 1;
            if (countIdx.containsKey(cur)) {
                max = Math.max(max, i - countIdx.get(cur));
            } else {
                countIdx.put(cur, i);
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{0,1,1,0,0,0,0,1,0,1,1,0,1};
        int res = s.findMaxLength(nums);
        System.out.println(res);
    }
}
