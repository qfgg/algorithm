import java.util.*;


class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = nums.length, i, k;
        int[] ret = new int[n];
        Arrays.fill(ret, Integer.MIN_VALUE);
        for (k = 0; k < 2; k++) {
            for (i = 0; i < n; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    ret[stack.pop()] = nums[i];
                }
                if (ret[i] == Integer.MIN_VALUE) {
                    stack.push(i);
                    ret[i] = -1;
                }
            }
        }
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,2,3,4,3};
        int[] ret = s.nextGreaterElements(nums);
        System.out.println(Arrays.toString(ret));
    }
}
