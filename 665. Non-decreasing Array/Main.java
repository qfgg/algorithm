import java.util.*;


class Solution {
    public boolean checkPossibility(int[] nums) {
        int n = nums.length, i, pos = -1;
        for (i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            return true;
        }
        for (i = pos + 1; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        if (pos == 0 ||
            pos == n - 2 ||
            nums[pos - 1] <= nums[pos + 1] ||
            nums[pos] <= nums[pos + 2]) {
            return true;
        }
        return false;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] nums = new int[]{5,7,1,8};
      boolean res = s.checkPossibility(nums);
      System.out.println(res);
  }
}

