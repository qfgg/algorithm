import java.util.*;


class Solution {
    boolean isPd(String s, int start, int end) {
        char lc, rc;
        while (start < end) {
            lc = s.charAt(start);
            rc = s.charAt(end);
            if (lc != rc) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    void append(List<List<String>> pre, String rs, List<List<String>> cur) {
        for (List<String> subList : pre) {
            List<String> copy = new ArrayList<>(subList);
            copy.add(rs);
            cur.add(copy);
        }
    }
    public List<List<String>> partition(String s) {
        Map<Integer, List<List<String>>> dp = new HashMap<>();
        int i, j, len = s.length();
        String rs;
        List<List<String>> first = new ArrayList<>(), pre, cur;
        first.add(new ArrayList<>(List.of(s.substring(0, 1))));
        dp.put(0, first);
        for (i = 1; i < len; i++) {
            cur = new ArrayList<>();
            if (isPd(s, 0, i)) {
                cur.add(new ArrayList<>(List.of(s.substring(0, i + 1))));
            }
            for (j = 0; j < i; j++) {
                if (isPd(s, j + 1, i)) {
                    pre = dp.get(j);
                    rs = s.substring(j + 1, i + 1);
                    append(pre, rs, cur);
                }
            }
            dp.put(i, cur);
        }
        return dp.get(len - 1);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "aabaa";
        List<List<String>> res = s.partition(str);
        System.out.println(res);
    }
}
