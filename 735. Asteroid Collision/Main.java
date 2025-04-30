import java.util.*;


class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> q = new ArrayDeque<>();
        int n = asteroids.length, i;
        boolean shouldDrop;
        for (i = 0; i < n; i++) {
            shouldDrop = false;
            while (!q.isEmpty() && q.peek() > 0 && asteroids[i] < 0) {
                if (q.peek() + asteroids[i] == 0) {
                    q.pop();
                    shouldDrop = true;
                    break;
                } else if (q.peek() + asteroids[i] > 0) {
                    shouldDrop = true;
                    break;
                } else {
                    q.pop();
                }
            }
            if (!shouldDrop) {
                q.push(asteroids[i]);
            }
        }
        n = q.size();
        int[] res = new int[n];
        for (i = 0; i < n; i++) {
            res[i] = q.pollLast();
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] asteroids = new int[]{-2,-1,1,2};
        int[] res = s.asteroidCollision(asteroids);
        System.out.println(Arrays.toString(res));
    }
}
