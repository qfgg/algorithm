import java.util.*;


class Solution {

    // convert a*, b*, ... to A, B, ...; .* to #
    // 1: matched char or '.', -1: unmatched char.
    // 0: matched a*, b*, ...
    // -2: if a*, b*, ... not matched with the current char
    // use previous pattern char's matching value if possible
    // -9: N/A
    private char[] preprocessPattern(String p) {
        List<Character> l = new ArrayList<>();
        char[] original = p.toCharArray();
        int n = original.length, i = 0, diff = 'A' - 'a';
        if (n == 1) {
            return original;
        }
        while (i < n - 1) {
            if (original[i + 1] == '*') {
                if (original[i] >= 'a' && original[i] <= 'z') {
                    l.add((char)(original[i] + diff));
                } else {
                    l.add('#');
                }
                i = i + 2;
            } else {
                l.add(original[i]);
                i++;
            }
        }
        if (original[n - 1] != '*') {
            l.add(original[n - 1]);
        }
        n = l.size();
        char[] res = new char[n];
        for (i = 0; i < n; i++) {
            res[i] = l.get(i);
        }
        return res;
    }
    public boolean isMatch(String s, String p) {
        char[] sc = s.toCharArray();
        char[] pc = preprocessPattern(p);
        int i, j, sl = sc.length, pl = pc.length, diff = 'A' - 'a';
        int[][] dp = new int[pl][sl];
        if (sc[0] == pc[0] || pc[0] == '.') {
            dp[0][0] = 1;
        } else if (pc[0] - sc[0] == diff || pc[0] == '#') {
            dp[0][0] = 0;
        } else if (pc[0] >= 'a' && pc[0] <= 'z') {
            dp[0][0] = -1;
        } else {
            dp[0][0] = -2;
        }
        for (i = 1; i < sl; i++) {
            if (dp[0][i - 1] == 1 || dp[0][i - 1] == -1 || dp[0][i - 1] == -9) {
                dp[0][i] = -1;
            } else if (dp[0][i - 1] == -2) {
                dp[0][i] = -9;
            } else if (dp[0][i - 1] == 0) {
                if (pc[0] - sc[i] == diff || pc[0] == '#') {
                    dp[0][i] = 0;
                } else {
                    dp[0][i] = -2;
                }
            }
        }
        for (j = 1; j < pl; j++) {
            if (dp[j - 1][0] == -1) {
                dp[j][0] = -1;
            } else if (dp[j - 1][0] == 1) {
                if (pc[j] == '.' || (pc[j] >= 'a' && pc[j] <= 'z')) {
                    dp[j][0] = -1;
                } else {
                    dp[j][0] = 1;
                }
            } else if (dp[j - 1][0] == -2 || dp[j - 1][0] == 0) {
                if (sc[0] == pc[j] || pc[j] == '.') {
                    dp[j][0] = 1;
                } else if (pc[j] - sc[0] == diff || pc[j] == '#') {
                    dp[j][0] = 0;
                } else if (pc[j] >= 'a' && pc[j] <= 'z') {
                    dp[j][0] = -1;
                } else {
                    if (dp[j - 1][0] == -2) {
                        dp[j][0] = j > 1 ? dp[j - 2][0] : -2;
                    } else if (dp[j - 1][0] == 0) {
                        dp[j][0] = 1;
                    }
                }
            }
        }
        for (j = 1; j < pl; j++) {
            for (i = 1; i < sl; i++) {
                if (dp[j - 1][i - 1] == 1) {
                    if (sc[i] == pc[j] || pc[j] == '.') {
                        dp[j][i] = 1;
                    } else if (pc[j] - sc[i] == diff || pc[j] == '#') {
                        dp[j][i] = 0;
                    } else if (pc[j] >= 'a' && pc[j] <= 'z') {
                        dp[j][i] = -1;
                    } else {
                        dp[j][i] = -2;
                    }
                } else if (dp[j - 1][i - 1] == 0 && (pc[j] >= 'a' && pc[j] <= 'z' || pc[j] == '.')) {
                    dp[j][i] = sc[i] == pc[j] || pc[j] == '.' ? 1 : -1;
                } else if (dp[j - 1][i - 1] == 0 && (pc[j] - sc[i] == diff || pc[j] == '#')) {
                    dp[j][i] = 0;
                } else if (dp[j - 1][i - 1] == -2 &&
                        j > 1 &&
                        dp[j - 2][i - 1] == -1 &&
                        (pc[j] >= 'a' && pc[j] <= 'z' || pc[j] == '.')) {
                    dp[j][i] = -1;
                } else if (dp[j - 1][i - 1] == -2 &&
                        j > 1 &&
                        dp[j - 2][i - 1] == 0 &&
                        (pc[j] >= 'a' && pc[j] <= 'z' || pc[j] == '.')) {
                    dp[j][i] = sc[i] == pc[j] || pc[j] == '.' ? 1 : -1;
                } else if (dp[j - 1][i - 1] == -2 &&
                        j > 1 &&
                        dp[j - 2][i - 1] == 1 &&
                        (pc[j] >= 'a' && pc[j] <= 'z' || pc[j] == '.')) {
                    dp[j][i] = sc[i] == pc[j] || pc[j] == '.' ? 1 : -1;
                } else {
                    if (dp[j][i - 1] == 0) {
                        if (pc[j] - sc[i] == diff || pc[j] == '#') {
                            dp[j][i] = 0;
                        } else {
                            dp[j][i] = -2;
                        }
                    } else if (dp[j][i - 1] == 1) {
                        if (pc[j] - sc[i] == diff || pc[j] == '#') {
                            dp[j][i] = 0;
                        } else if (pc[j] >= 'a' && pc[j] <= 'z') {
                            dp[j][i] = -1;
                        } else {
                            dp[j][i] = -2;
                        }
                    } else if (dp[j - 1][i] == 0 || dp[j - 1][i] == -2) {
                        if (sc[i] == pc[j] || pc[j] == '.') {
                            dp[j][i] = 1;
                        } else if (pc[j] - sc[i] == diff || pc[j] == '#') {
                            dp[j][i] = 0;
                        } else if (pc[j] >= 'a' && pc[j] <= 'z') {
                            dp[j][i] = -1;
                        } else {
                            if (dp[j - 1][i] == -2) {
                                dp[j][i] = j > 1 ? dp[j - 2][i] : -2;
                            } else if (dp[j - 1][i] == 0) {
                                dp[j][i] = 1;
                            }
                        }
                    } else if (dp[j - 1][i] == 1) {
                        if (pc[j] == '.' || (pc[j] >= 'a' && pc[j] <= 'z')) {
                            dp[j][i] = -1;
                        } else {
                            dp[j][i] = 1;
                        }
                    } else if (dp[j][i - 1] == -2) {
                        dp[j][i] = -9;
                    } else {
                        dp[j][i] = -1;
                    }
                }
            }
        }
        return dp[j - 1][i - 1] == 1 || dp[j - 1][i - 1] == 0;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "bbcacbabbcbaaccabc";
        String p = "b*a*a*.c*bb*b*.*.*";
        boolean res = s.isMatch(str, p);
        System.out.println(res);
    }
}
