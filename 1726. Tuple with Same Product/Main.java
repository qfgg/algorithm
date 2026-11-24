import java.util.*;


class Solution {
    public int tupleSameProduct(int[] nums) {
        int i, j, n = nums.length, curPrd, cnt = 0, val;
        Map<Integer, Integer> prd = new HashMap<>();
        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                curPrd = nums[i] * nums[j];
                if (prd.containsKey(curPrd)) {
                    val = prd.get(curPrd);
                    prd.put(curPrd, val + 1);
                    cnt += val * 8;
                } else {
                    prd.put(curPrd, 1);
                }
            }
        }
        return cnt;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] nums = new int[]{2,3,4,6,8,12};
      int res = s.tupleSameProduct(nums);
      System.out.println(res);
  }
}
