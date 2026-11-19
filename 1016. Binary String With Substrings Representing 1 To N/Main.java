import java.util.*;


class Solution {
    int cnt = 0;
    Set<Integer> nums = new HashSet<>();
    void add(int val, int n) {
        if (val > 0 && val <= n && !nums.contains(val)) {
            nums.add(val);
            cnt++;
        }
    }
    public boolean queryString(String s, int n) {
        int i, l = s.length(), base = 1, zn, val;
        int[] digits = new int[l];
        for (i = 0; i < l; i++) {
            digits[i] = s.charAt(i) - '0';
        }
        zn = Math.min(l - 1, 30);
        for (i = 0; i < zn; i++) {
            base = base << 1;
        }
        while (zn >= 0) {
            val = 0;
            for (i = 0; i <= zn; i++) {
                val = val * 2 + digits[i];
            }
            add(val, n);
            if (cnt == n) {
                return true;
            }
            for (i = 1; i < l - zn; i++) {
                if (digits[i - 1] == 1) {
                    val -= base;
                }
                val = val << 1;
                val += digits[i + zn];
                add(val, n);
                if (cnt == n) {
                    return true;
                }
            }
            base = base >> 1;
            zn--;
        }
        return false;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      String str = "1";
      boolean res = s.queryString(str, 2);
      System.out.println(res);
  }
}
