import java.util.*;


class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 1) {
            return 0;
        }
        int i, max = 0;
        for (i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] prices = new int[]{7,1,5,3,6,4};
        int res = s.maxProfit(prices);
        System.out.println(res);
    }
}
