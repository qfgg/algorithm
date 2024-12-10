import java.util.*;

class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length, cur;
        Map<Integer, Integer> diffCnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            cur = nums[i] - i;
            if (diffCnt.containsKey(cur)) {
                diffCnt.put(cur, diffCnt.get(cur) + 1);
            } else {
                diffCnt.put(cur, 1);
            }
        }
        long res = (long)n * (n - 1) / 2;
        for (Integer k : diffCnt.keySet()) {
            cur = diffCnt.get(k);
            if (cur > 1) {
                res = res - (long)cur * (cur - 1) / 2;
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{4,1,3,3};
        long res = s.countBadPairs(nums);
        System.out.println(res);
    }
}
