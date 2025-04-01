import java.util.*;

class Solution {
    public boolean canJump(int[] nums) {
        int cur = 0, nextBoundary = nums[0], nextMax, i;
        while (true) {
            nextMax = nextBoundary;
            for (i = cur + 1; i < nums.length && i <= nextBoundary; i++) {
                nextMax = Math.max(nextMax, i + nums[i]);
            }
            if (nextMax >= nums.length - 1) {
                return true;
            }
            if (cur == nextBoundary) {
                break;
            }
            cur = nextBoundary;
            nextBoundary = nextMax;
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = { 5,9,3,2,1,0,2,3,3,1,0,0 };
        boolean res = s.canJump(nums);
        System.out.println(res);
    }
}
