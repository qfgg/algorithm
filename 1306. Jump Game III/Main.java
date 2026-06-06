import java.util.*;


class Solution {
    boolean[] memo;
    boolean[] visited;
    boolean dfs(int[] arr, int start, boolean[] memo, boolean[] visited) {
        if (visited[start]) {
            return memo[start];
        }
        if (arr[start] == 0) {
            memo[start] = true;
            return true;
        }
        visited[start] = true;
        boolean left = false, right = false, ret;
        if (start - arr[start] >= 0) {
            left = dfs(arr, start - arr[start], memo, visited);
        }
        if (start + arr[start] < arr.length) {
            right = dfs(arr, start + arr[start], memo, visited);
        }
        ret = left || right;
        memo[start] = ret;
        return memo[start];
    }
    public boolean canReach(int[] arr, int start) {
        int len = arr.length;
        memo = new boolean[len];
        visited = new boolean[len];
        return dfs(arr, start, memo, visited);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[]{3,0,2,1,2};
        int start = 2;
        boolean res = s.canReach(arr, start);
        System.out.println(res);
    }
}
