import java.util.*;


class Solution {
    public int[] resultsArray(int[] nums, int k) {
        Deque<Integer> q = new ArrayDeque<>();
        int i = 0, n = nums.length;
        while (i < k) {
            while (!q.isEmpty() && nums[i] != nums[q.peek()] + 1) {
                q.pop();
            }
            q.push(i);
            i++;
        }
        int resN = n - k + 1, rightEnd;
        int[] res = new int[resN];
        res[0] = q.size() == k ? nums[q.getFirst()] : -1;
        for (i = 1; i < resN; i++) {
            while (!q.isEmpty() && q.getLast() < i) {
                q.removeLast();
            }
            rightEnd = i + k - 1;
            while (!q.isEmpty() && nums[rightEnd] != nums[q.peek()] + 1) {
                q.pop();
            }
            q.push(rightEnd);
            res[i] = q.size() == k ? nums[q.getFirst()] : -1;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,3,4};
        int k = 2;
        int[] res = s.resultsArray(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
