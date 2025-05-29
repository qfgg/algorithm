import java.util.*;


class Solution {
    private void search(char[] c, String word, int idx, HashMap<Integer, List<Integer>> match) {
        char[] wc = word.toCharArray();
        int n = c.length, wn = wc.length, i, j, k;
        for (i = n - 1; i >= wn - 1; i--) {
            j = i;
            k = wn - 1;
            while (k >= 0) {
                if (c[j] != wc[k]) {
                    break;
                }
                j--;
                k--;
            }
            if (k < 0) {
                if (match.containsKey(i)) {
                    match.get(i).add(idx);
                } else {
                    match.put(i, new ArrayList<>(List.of(idx)));
                }
            }
        }
    }
    public int minExtraChar(String s, String[] dictionary) {
        char[] c = s.toCharArray();
        HashMap<Integer, List<Integer>> match = new HashMap<>();
        int n = dictionary.length, i, k, j;
        for (i = 0; i < n; i++) {
            search(c, dictionary[i], i, match);
        }
        n = c.length;
        List<Integer> indices;
        int[] dp = new int[n];
        for (i = 0; i < n; i++) {
            dp[i] = i == 0 ? 1 : dp[i - 1] + 1;
            if (match.containsKey(i)) {
                indices = match.get(i);
                k = indices.size();
                for (j = 0; j < k; j++) {
                    dp[i] = i - dictionary[indices.get(j)].length() < 0 ? 0 : Math.min(dp[i], dp[i - dictionary[indices.get(j)].length()]);
                }
            }
        }
        return dp[n - 1];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "kevlplxozaizdhxoimmraiakbak";
        String[] dictionary = new String[]{
                "yv","bmab","hv","bnsll","mra","jjqf","g",
                "aiyzi","ip","pfctr","flr","ybbcl","biu","ke",
                "lpl","iak", "pirua","ilhqd","zdhx","fux","xaw",
                "pdfvt","xf","t","wq", "r","cgmud","aokas","xv",
                "jf","cyys","wcaz","rvegf","ysg", "xo","uwb","lw",
                "okgk","vbmi","v","mvo","fxyx","ad","e"
        };
        int res = s.minExtraChar(str, dictionary);
        System.out.println(res);
    }
}
