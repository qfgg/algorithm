import java.util.*;


class Solution {
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int i, n = nums.length, cur;
        long score = 0;
        for (i = 0; i < n; i++) {
            maxHeap.add(nums[i]);
        }
        for (i = 0; i < k; i++) {
            cur = maxHeap.poll();
            maxHeap.add((int)Math.ceil((double) cur / 3));
            score += cur;
        }
        return score;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,10,3,3,3};
        int k = 3;
        long res = s.maxKelements(nums, k);
        System.out.println(res);
    }
}
