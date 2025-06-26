import java.util.*;


class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stk = new ArrayDeque<>();
        int i = 0, j = 0, n = pushed.length;
        while (j < n) {
            if (i < n && pushed[i] == popped[j]) {
                i++;
                j++;
            } else if (!stk.isEmpty() && stk.peek() == popped[j]) {
                stk.pop();
                j++;
            } else {
                if (i == n) {
                    return false;
                }
                stk.push(pushed[i]);
                i++;
            }
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] pushed = new int[]{1,2,3,4,5};
        int[] popped = new int[]{4,5,3,2,1};
        boolean res = s.validateStackSequences(pushed, popped);
        System.out.println(res);
    }
}
