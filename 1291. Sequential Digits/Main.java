import java.util.*;


class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ret = new ArrayList<>();
        if (low == 1000000000) {
            return ret;
        }
        int tmp, inc = 1, base = 1, cur, last;
        tmp = low;
        while (tmp >= 10) {
            tmp = tmp / 10;
            inc = inc * 10 + 1;
            base = base * 10 + base % 10 + 1;
        }
        tmp = high;
        while (tmp >= 10) {
            tmp = tmp / 10;
        }
        cur = base;
        last = cur % 10;
        while (cur < low) {
            if (last < 9) {
                cur += inc;
                last++;
            } else if (base % 10 < 9) {
                last = base % 10 + 1;
                base = base * 10 + last;
                inc = inc * 10 + 1;
                cur = base;
            } else {
                break;
            }
        }
        if (cur < low) {
            return ret;
        }
        while (cur <= high) {
            ret.add(cur);
            if (last < 9) {
                cur += inc;
                last++;
            } else if (base % 10 < 9) {
                last = base % 10 + 1;
                base = base * 10 + last;
                inc = inc * 10 + 1;
                cur = base;
            } else {
                break;
            }
        }
        return ret;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int low = 8511, high = 23553;
      List<Integer> res = s.sequentialDigits(apples, days);
      System.out.println(res);
  }
}
