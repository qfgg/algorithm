import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length, res = 0;
        int[] pre = new int[n];
        Map<Integer, Integer> cnt = new HashMap<>();
        pre[0] = nums[0];
        cnt.put(pre[0], 1);
        if (pre[0] == k) {
            res++;
        }
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
            if (pre[i] == k) {
                res++;
            }
            if (cnt.containsKey(pre[i] - k)) {
                res += cnt.get(pre[i] - k);
            }
            cnt.merge(pre[i], 1, Integer::sum);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{-1,-1,1};
        int k = 0;
        int res = s.subarraySum(nums, k);
        System.out.println(res);
    }
}
