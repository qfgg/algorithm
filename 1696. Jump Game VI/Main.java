import java.util.*;


class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length, i, premax;
        int[] max = new int[n];
        Deque<Integer> q = new ArrayDeque<>();
        max[0] = nums[0];
        q.add(0);
        for (i = 1; i < n; i++) {
            while (i - q.getLast() > k) {
                q.removeLast();
            }
            premax = max[q.getLast()];
            max[i] = nums[i] + premax;
            while (!q.isEmpty() && max[i] >= max[q.peek()]) {
                q.pop();
            }
            q.push(i);
        }
        return max[n - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,-1,-2,4,-7,3};
        int k = 2;
        int res = s.maxResult(nums, k);
        System.out.println(res);
    }
}
