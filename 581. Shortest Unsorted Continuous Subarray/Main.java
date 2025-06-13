import java.util.*;


class Solution {
    public int findUnsortedSubarray(int[] nums) {
        Deque<Integer> left = new ArrayDeque<>(), right = new ArrayDeque<>();
        int n = nums.length, i, pre, cur, l, r;
        boolean keepLeft = true, keepRight = true;
        for (i = 0; i < n; i++) {
            while (!left.isEmpty() && nums[i] < nums[left.peek()]) {
                left.pop();
            }
            left.push(i);
        }
        pre = left.removeLast();
        if (pre != 0) {
            keepLeft = false;
        }
        while (!left.isEmpty()) {
            cur = left.removeLast();
            if (cur != pre + 1) {
                break;
            }
            pre = cur;
        }
        l = pre;
        if (keepLeft && l == n - 1) {
            return 0;
        }
        for (i = n - 1; i >= 0; i--) {
            while (!right.isEmpty() && nums[i] > nums[right.peek()]) {
                right.pop();
            }
            right.push(i);
        }
        pre = right.removeLast();
        if (pre != n - 1) {
            keepRight = false;
        }
        while (!right.isEmpty()) {
            cur = right.removeLast();
            if (cur != pre - 1) {
                break;
            }
            pre = cur;
        }
        r = pre;
        if (!keepLeft && !keepRight) {
            return n;
        }
        if (!keepLeft) {
            return r;
        }
        if (!keepRight) {
            return n - l - 1;
        }
        return r - l - 1;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{2,1};
        int res = s.findUnsortedSubarray(nums);
        System.out.println(res);
    }
}
