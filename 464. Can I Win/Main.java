import java.util.*;


class Solution {
    Map<Integer, Boolean> memo = new HashMap<>();
    boolean dfs(int bit, int k, int target) {
        if (target <= 0) {
            return false;
        }
        if (memo.containsKey(bit)) {
            return memo.get(bit);
        }
        int mask;
        for (int i = k - 1; i >= 0; i--) {
            mask = 1 << i;
            if ((bit & mask) > 0) {
                if (!dfs(bit ^ mask, k, target - i - 1)) {
                    memo.put(bit, true);
                    return true;
                }
            }
        }
        memo.put(bit, false);
        return false;
    }
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal) {
            return false;
        }
        int bit = (1 << maxChoosableInteger) - 1;
        return dfs(bit, maxChoosableInteger, desiredTotal);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        boolean res = s.canIWin(10, 11);
        System.out.println(res);
    }
}
