import java.util.*;


class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        char[] ch = s.toCharArray();
        int n = ch.length, i;
        HashSet<String> uniques = new HashSet<>();
        HashSet<String> added = new HashSet<>();
        List<String> res = new ArrayList<>();
        String cur;
        for (i = 0; i < n - 9; i++) {
            cur = new String(ch, i, 10);
            if (!added.contains(cur)) {
                if (uniques.contains(cur)) {
                    res.add(cur);
                    added.add(cur);
                } else {
                    uniques.add(cur);
                }
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "AAAAAAAAAAAAA";
        List<String> res = s.findRepeatedDnaSequences(str);
        System.out.println(res);
    }
}
