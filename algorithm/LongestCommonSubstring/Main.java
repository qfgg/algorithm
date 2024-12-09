public class Main {
    public static int longestCommonSubstring(String str1, String str2) {
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int len1 = c1.length;
        int len2 = c2.length;
        int[][] dp = new int[len1][len2];
        int max = 0;
        int i;
        int j;

        for (j = 0; j < len2; j++) {
            if (c1[0] == c2[j]) {
                dp[0][j] = 1;
                if (dp[0][j] > max) {
                    max = dp[0][j];
                }
            } else {
                dp[0][j] = 0;
            }
        }

        for (i = 1; i < len1; i++) {
            if (c2[0] == c1[i]) {
                dp[i][0] = 1;
                if (dp[i][0] > max) {
                    max = dp[i][0];
                }
            } else {
                dp[i][0] = 0;
            }
        }

        for (i = 1; i < len1; i++) {
            for (j = 1; j < len2; j++) {
                if (c1[i] == c2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int ret = longestCommonSubstring("ABABCBC", "CBABCA");
        System.out.println(ret);
        ret = longestCommonSubstring("BADANAT", "CANADAS");
        System.out.println(ret);
    }
}
