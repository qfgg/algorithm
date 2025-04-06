import java.util.*;

class Solution {
    public int jump(int[] nums) {
        int rightmost = nums[0], i = 0, len = nums.length, steps = 0, next;
        if (len == 1) {
            return 0;
        }
        while (true) {
            steps++;
            if (rightmost >= len - 1) {
                break;
            }
            next = rightmost;
            for (i = i + 1; i <= rightmost && i < len; i++) {
                next = Math.max(next, i + nums[i]);
            }
            i = rightmost;
            rightmost = next;
        }
        return steps;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = { 8,2,4,4,4,9,5,2,5,8,8,0,8,6,9,1,1,6,3,5,1,2,6,6,0,4,8,6,0,3,2,8,7,6,5,1,7,0,3,4,8,3,5,9,0,4,0,1,0,5,9,2,0,7,0,2,1,0,8,2,5,1,2,3,9,7,4,7,0,0,1,8,5,6,7,5,1,9,9,3,5,0,7,5 };
        int res = s.jump(nums);
        System.out.println(res);
    }
}
