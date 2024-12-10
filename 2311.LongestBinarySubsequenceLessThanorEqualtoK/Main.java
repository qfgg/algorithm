import java.util.*;

public class Main {
    public static int longestSubsequence(String s, int k) {
        int r = s.length() - 1, i, leftZeroCnt = 0, sufZeroCnt = 0, shift = 0, tmp, max = 0;
        long power = 1, sum = 0;
        char c;
        boolean isEnd = false;
        List<Long> ones = new ArrayList<>();
        while (r >= 0 && s.charAt(r) == '0') {
            sufZeroCnt++;
            r--;
        }
        if (r < 0) {
            return s.length();
        }
        while (r >= 0) {
            c = s.charAt(r);
            if (!isEnd && c == '1') {
                if (sum + power > k) {
                    isEnd = true;
                } else {
                    sum += power;
                    ones.add(power);
                }
            } else if (c == '0') {
                leftZeroCnt++;
            }
            power = power << 1;
            r--;
        }
        tmp = leftZeroCnt + ones.size();
        i = ones.size() - 1;
        while (i >= 0 || sufZeroCnt > 0) {
            while (sufZeroCnt > 0 && (sum << 1) <= k) {
                sum = sum << 1;
                tmp++;
                shift++;
                sufZeroCnt--;
            }
            if (tmp > max) {
                max = tmp;
            }
            if (i >= 0) {
                sum -= (ones.get(i) << shift);
                i--;
                tmp--;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        String s = "00101001";
        int k = 1;
        int res = longestSubsequence(s, k);
        System.out.println(res);
    }
}
