import java.util.*;


class Solution {
    public int shortestSubarray(int[] nums, int k) {
        Deque<Integer> ms = new ArrayDeque<>();
        List<Integer> candidates = new ArrayList<>();
        int n = nums.length, i, wmin = Integer.MAX_VALUE, top, l = -1, r = -1;
        long[] psum = new long[n];
        psum[0] = nums[0];
        for(i = 1; i < n; i++) {
            psum[i] += psum[i - 1] + (long)nums[i];
        }
        for(i = 0; i < n; i++) {
            while(!ms.isEmpty() && psum[i] <= psum[ms.peek()]) {
                top = ms.pop();
                if (top == candidates.get(r)) {
                    r--;
                    if (l > r) {
                        l = r;
                    }
                }
            }
            ms.push(i);
            if (l == -1) {
                l = 0;
                r = 0;
            } else {
                r++;
            }
            if (r < candidates.size()) {
                candidates.set(r, i);
            } else {
                candidates.add(i);
            }
            if (psum[i] >= k) {
                wmin = Math.min(wmin, i + 1);
            }
            while (l < r && psum[candidates.get(r)] - psum[candidates.get(l)] >= k) {
                wmin = Math.min(wmin, candidates.get(r) - candidates.get(l));
                l++;
            }
        }
        return wmin == Integer.MAX_VALUE ? -1 : wmin;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{-58, 31, -35, 70, -24, 28, 94, -42, 17, -7, 75, 29, -21, -67, -72, 78, -3, -38, -38, -43};
        int k = 183;
        int res = s.shortestSubarray(nums, k);
        System.out.println(res);
    }
}
