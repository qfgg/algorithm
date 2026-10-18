import java.util.*;


class Solution {
    int count(char[] ch, int n, int start) {
        int i, cnt = 0;
        for (i = start; i < n; i += 2) {
            if (ch[i] != '0') {
                cnt++;
            }
        }
        return cnt;
    }
    public int minSwaps(String s) {
        if (s.length() == 1) {
            return 0;
        }
        char[] ch = s.toCharArray();
        int i, n = ch.length, cnt1 = 0, cnt0 = 0;
        for (i = 0; i < n; i++) {
            if (ch[i] == '0') {
                cnt0++;
            } else {
                cnt1++;
            }
        }
        if (Math.abs(cnt1 - cnt0) > 1) {
            return -1;
        }
        if (cnt1 > cnt0) {
            return count(ch, n, 1);
        }
        if (cnt0 > cnt1) {
            return count(ch, n, 0);
        }
        return Math.min(count(ch, n, 0), count(ch, n, 1));
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      String str = "111000";
      int res = s.minSwaps(str);
      System.out.println(res);
  }
}

