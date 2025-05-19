import java.util.*;


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if (n == 1) {
            return triangle.getFirst().getFirst();
        }
        int i, j, cur, minTotal = Integer.MAX_VALUE;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = triangle.getFirst().getFirst();
        for (i = 1; i < n; i++) {
            for (j = i; j >= 0; j--) {
                cur = triangle.get(i).get(j);
                dp[j] = (j == 0 ? dp[j] : Math.min(dp[j], dp[j - 1])) + cur;
                if (i == n - 1) {
                    minTotal = Math.min(minTotal, dp[j]);
                }
            }
        }
        return minTotal;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<>(List.of(2)));
        triangle.add(new ArrayList<>(List.of(3,4)));
        triangle.add(new ArrayList<>(List.of(6,5,7)));
        triangle.add(new ArrayList<>(List.of(4,1,8,3)));
        int res = s.minimumTotal(triangle);
        System.out.print(res);
    }
}
