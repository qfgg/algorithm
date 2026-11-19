import java.util.*;


class Solution {
    public int minSwaps(int[] nums) {
        int i, j, n = nums.length, cnt = 0, min = 0, cur;
        for (i = 0; i < n; i++) {
            if (nums[i] == 1) {
                cnt++;
            }
        }
        for (i = 0; i < cnt; i++) {
            if (nums[i] == 0) {
                min++;
            }
        }
        cur = min;
        for (i = 1; i < n; i++) {
            j = (i + cnt - 1) % n;
            if (nums[i - 1] == 0) {
                cur--;
            }
            if (nums[j] == 0) {
                cur++;
            }
            min = Math.min(min, cur);
        }
        return min;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] nums = new int[]{0,1,1,1,0,0,1,1,0};
      int res = s.minSwaps(houses, heaters);
      System.out.println(res);
  }
}
