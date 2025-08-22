import java.util.*;


class Solution {
    int findFrom(int start, int n, int k) {
        if (k == 1) {
            return start;
        }
        long cur = start, next = start, cnt = 1, add;
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
    public int findKthNumber(int n, int k) {
        if (k == 1) {
            return 1;
        }
        if (n < 10) {
            return findFrom(1, n, k);
        }
        int firstDigit, mul = 1, num = n, cnt, remain = k, i, j;
        while (num >= 10) {
            num = num / 10;
            mul = mul * 10;
        }
        firstDigit = num;
        i = 1;
        while (i < 10) {
            j = mul;
            cnt = 0;
            if (i < firstDigit) {
                cnt += j;
            } else if (i == firstDigit) {
                cnt += n - i * j + 1;
            }
            j = j / 10;
            while (j >= 10) {
                cnt += j;
                j = j / 10;
            }
            cnt++;
            if (cnt >= remain) {
                break;
            }
            remain -= cnt;
            i++;
        }
        return findFrom(i, n, remain);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 9, k = 9;
        int res = s.findKthNumber(n, k);
        System.out.println(res);
    }
}
