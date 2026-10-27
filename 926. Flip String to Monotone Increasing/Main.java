import java.util.*;


class Solution {
    public int minFlipsMonoIncr(String s) {
        char[] ch = s.toCharArray();
        int i, n = ch.length, min, pre = 0;
        int[] premin = new int[n];
        for (i = 0; i < n; i++) {
            if (ch[i] == '1') {
                pre++;
            }
            premin[i] = pre;
        }
        pre = 0;
        min = premin[n - 1];
        for (i = n - 1; i >= 0; i--) {
            if (ch[i] == '0') {
                pre++;
            }
            min = i == 0 ? Math.min(min, pre) : Math.min(min, premin[i - 1] + pre);
        }
        return min;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      String str = "010110";
      int res = s.minFlipsMonoIncr(str);
      System.out.println(res);
  }
}
