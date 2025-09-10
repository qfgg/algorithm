import java.util.*;


class Solution {
    boolean hasCommon(String a, String b) {
        boolean[] letters = new boolean[26];
        int an = a.length(), bn = b.length(), i;
        for (i = 0; i < an; i++) {
            letters[a.charAt(i) - 'a'] = true;
        }
        for (i = 0; i < bn; i++) {
            if (letters[b.charAt(i) - 'a']) {
                return true;
            }
        }
        return false;
    }
    public int maxProduct(String[] words) {
        int i, j, n = words.length, max = 0;
        for (i = 0; i < n - 1; i++) {
            for (j = i + 1; j < n; j++) {
                if (!hasCommon(words[i], words[j])) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] words = {"a","ab","abc","d","cd","bcd","abcd"};
        int res = s.maxProduct(words);
        System.out.println(res);
    }
}
