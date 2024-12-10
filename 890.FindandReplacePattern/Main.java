import java.util.*;

class Solution {
    List<Integer> getPatternCode(String target, List<Integer> pt) {
        int[] codeMap = new int[26];
        List<Integer> res = new ArrayList<>();
        int n = target.length(), i, idx, code = 1;
        for (i = 0; i < n; i++) {
            idx = target.charAt(i) - 'a';
            if (codeMap[idx] == 0) {
                codeMap[idx] = code;
                code++;
            }
            if (pt != null && pt.get(i) != codeMap[idx]) {
                return null;
            }
            res.add(codeMap[idx]);
        }
        return res;
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        List<Integer> ref = getPatternCode(pattern, null);
        for (String word : words) {
            if (getPatternCode(word, ref) != null) {
                res.add(word);
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] words = new String[]{"abc","deq","mee","aqq","dkd","ccc"};
        String pattern = "abb";
        List<String> res = s.findAndReplacePattern(words, pattern);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
