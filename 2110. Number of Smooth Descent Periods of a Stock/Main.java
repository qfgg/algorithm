import java.util.*;


class Solution {
    public long getDescentPeriods(int[] prices) {
        long cnt = 1;
        int i, n = prices.length, len = 1;
        for (i = 1; i < n; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                len++;
            } else {
                len = 1;
            }
            cnt += len;
        }
        return cnt;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] prices = new int[]{3,2,1,4};
      long res = s.getDescentPeriods(prices);
      System.out.println(res);
  }
}
