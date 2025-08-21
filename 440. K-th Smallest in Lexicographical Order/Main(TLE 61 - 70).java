import java.util.*;


class Solution {
    public int findKthNumber(int n, int k) {
        if (k == 1) {
            return 1;
        }
        long cur = 1, next = 1, cnt = 1, add;
        while (cnt <= n) {
            next = next * 10;
            while (next <= n) {
                cur = next;
                cnt++;
                if (cnt == k) {
                    return (int)cur;
                }
                next = next * 10;
            }
            next = Math.min(n, cur - cur % 10 + 9);
            add = next - cur;
            if (cnt + add >= k) {
                return (int)(cur + k - cnt);
            }
            cnt += add;
            next = next == n ? next / 10 + 1 : next + 1;
            while (next % 10 == 0) {
                next = next / 10;
            }
            cur = next;
            cnt++;
            if (cnt == k) {
                return (int)cur;
            }
        }
        return (int)cur;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 1000000000, k = 1000000000;
        int res = s.findKthNumber(n, k);
        System.out.println(res);
    }
}
