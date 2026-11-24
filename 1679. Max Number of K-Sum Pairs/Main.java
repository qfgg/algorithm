import java.util.*;


class Solution {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        int i, n = nums.length, pair, cur, cnt = 0;
        for (i = 0; i < n; i++) {
            if (nums[i] >= k) {
                continue;
            }
            pair = k - nums[i];
            if (count.containsKey(pair)) {
                cur = count.get(pair);
                if (cur == 1) {
                    count.remove(pair);
                } else {
                    count.put(pair, cur - 1);
                }
                cnt++;
            } else {
                count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            }
        }
        return cnt;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] nums = new int[]{2,1,1,1,2,1,2,2,2,2};
      int k = 3;
      int res = s.maxOperations(nums, k);
      System.out.println(res);
  }
}
