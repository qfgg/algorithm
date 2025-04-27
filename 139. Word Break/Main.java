import java.util.*;


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int sl = s.length(), i, wl;
        boolean[] dp = new boolean[sl];
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                dp[word.length() - 1] = true;
            }
        }
        for (i = 1; i < sl; i++) {
            for (String word : wordDict) {
                wl = word.length();
                if (i - wl + 1 >= 0 &&
                        s.startsWith(word, i - wl + 1) &&
                        (i - wl == -1 || dp[i - wl])) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[sl - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = new ArrayList<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));
        boolean res = s.wordBreak(str, wordDict);
        System.out.println(res);
    }
}
