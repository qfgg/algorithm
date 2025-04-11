import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        int max = 1, l1 = 0, cur, n = 0, up;
        double sqrt;
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            if (num == 1) {
                l1++;
            } else {
                if (count.containsKey(num)) {
                    count.put(num, count.get(num) + 1);
                } else {
                    count.put(num, 1);
                }
            }
        }
        if (l1 % 2 == 0) {
            l1--;
        }
        max = Math.max(l1, max);
        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(count.keySet());
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (count.get(cur) >= 1) {
                if (count.get(cur) == 1) {
                    n = 1;
                } else {
                    // search up
                    n = 2;
                    count.put(cur, 0);
                    up = cur * cur;
                    while (count.containsKey(up) && count.get(up) > 1) {
                        n = n + 2;
                        count.put(up, 0);
                        up = up * up;
                    }
                    if (!count.containsKey(up)) {
                        n--;
                    } else {
                        n++;
                    }
                }
                if (n >= 1) {
                    // search down
                    sqrt = Math.sqrt(cur);
                    while (Math.floor(sqrt) == sqrt && count.containsKey((int)sqrt) && count.get((int)sqrt) > 1) {
                        n = n + 2;
                        count.put((int)sqrt, 0);
                        sqrt = Math.sqrt(sqrt);
                    }
                }
                max = Math.max(n, max);
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{15,15,225,225,50625,50625};
        int res = s.maximumLength(nums);
        System.out.println(res);
    }
}
