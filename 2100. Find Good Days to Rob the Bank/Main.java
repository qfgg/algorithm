import java.util.*;


class Solution {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> days = new ArrayList<>();
        int i, n = security.length, preRLen = 0;
        if (time == 0) {
            for (i = 0; i < n; i++) {
                days.add(i);
            }
            return days;
        }
        int[] lLen = new int[n];
        for (i = 1; i < n - time; i++) {
            if (security[i] <= security[i - 1]) {
                lLen[i] = lLen[i - 1] + 1;
            } else {
                lLen[i] = 0;
            }
        }
        for (i = n - 2; i >= time; i--) {
            if (security[i] <= security[i + 1]) {
                preRLen++;
            } else {
                preRLen = 0;
            }
            if (lLen[i] >= time && preRLen >= time) {
                days.add(i);
            }
        }
        return days;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] security = new int[]{5,3,3,3,5,6,2};
      int time = 2;
      List<Integer> res = s.goodDaysToRobBank(security, time);
      System.out.println(res);
  }
}

