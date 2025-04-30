import java.util.*;


class Solution {
    public int maxProfit(int[] prices) {
        int i, n = prices.length, max = 0, leftMin = prices[0];
        for (i = 1; i < n; i++) {
            if (prices[i] - leftMin > max) {
                max = prices[i] - leftMin;
            }
            if (prices[i] < leftMin) {
                leftMin = prices[i];
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
