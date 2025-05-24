import java.util.*;


class Solution {
    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length, l = 0, r = 0, maxlen = 0, sum = 0;
        while (l <= r && r < n) {
            if (chars[r] == '(') {
                sum++;
                r++;
            } else {
                sum--;
                if (sum == 0) {
                    maxlen = Math.max(maxlen, r - l + 1);
                    r++;
                } else if (sum < 0) {
                    r++;
                    l = r;
                    sum = 0;
                } else {
                    r++;
                }
            }
        }
        l = n - 1;
        r = n - 1;
        sum = 0;
        while (l <= r && l >= 0) {
            if (chars[l] == ')') {
                sum++;
                l--;
            } else {
                sum--;
                if (sum == 0) {
                    maxlen = Math.max(maxlen, r - l + 1);
                    l--;
                } else if (sum < 0) {
                    l--;
                    r = l;
                    sum = 0;
                } else {
                    l--;
                }
            }
        }
        return maxlen;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "()((())";
        int res = s.longestValidParentheses(str);
        System.out.println(res);
    }
}
