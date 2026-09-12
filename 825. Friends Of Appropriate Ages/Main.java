import java.util.*;


class Solution {
    boolean isValid(int y, int x) {
        if (y <= 0.5 * x + 7 || y > x || (y > 100 && x < 100)) {
            return false;
        }
        return true;
    }
    public int numFriendRequests(int[] ages) {
        int n = ages.length;
        if (n == 1) {
            return 0;
        }
        Arrays.sort(ages);
        int l = 0, r = 1, total = 0, cnt = 0;
        while (l < r) {
            while (r < n && isValid(ages[l], ages[r])) {
                r++;
            }
            total += r - l - 1;
            l++;
            if (l == r && r < n - 1) {
                r++;
            }
        }
        for (l = 1; l < n; l++) {
            if (ages[l] < 15) {
                continue;
            }
            if (ages[l] == ages[l - 1]) {
                cnt++;
            }
            if (ages[l] != ages[l - 1] || l == n - 1) {
                if (cnt > 0) {
                    total += cnt * (1 + cnt) / 2;
                    cnt = 0;
                }
            }
        }
        return total;
    }
}
public class Main {
  public static void main(String[] args) {
      int[] ages = new int[]{16,16};
      Solution s = new Solution();
      int ret = s.numFriendRequests(ages);
      System.out.println(ret);
  }
}
