import java.util.*;


class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length ==  1) {
            return new int[1];
        }
        Deque<Integer> ms = new ArrayDeque<>();
        int i, n = temperatures.length, top;
        int[] res = new int[n];
        for (i = 0; i < n; i++) {
            while (!ms.isEmpty() && temperatures[i] > temperatures[ms.peek()]) {
                top = ms.pop();
                res[top] = i - top;
            }
            ms.push(i);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] temperatures = new int[]{73,74,75,71,69,72,76,73};
        int[] res = s.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(res));
    }
}
