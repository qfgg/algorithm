import java.util.*;


class Solution {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        HashSet<String> dict = new HashSet<>();
        int i, n = s.length();
        for (i = 1; i <= 26; i++) {
            dict.add(String.valueOf(i));
        }
        int[] dp = new int[n];
        dp[0] = 1;
        String substr1, substr2;
        for (i = 1; i < n; i++) {
            substr1 = s.substring(i, i + 1);
            substr2 = s.substring(i - 1, i + 1);
            if (dict.contains(substr1)) {
                dp[i] += dp[i - 1];
            }
            if (dict.contains(substr2)) {
                dp[i] += i == 1 ? 1 : dp[i - 2];
            }
        }
        return dp[n - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "10";
        int res = s.numDecodings(str);
        System.out.println(res);
    }
}
