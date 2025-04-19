import java.util.*;


class Solution {
    private List<Integer> find(String s, String t) {
        List<Integer> idx = new ArrayList<>();
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int sl = sc.length, tl = tc.length, i, j;
        for (i = 0; i <= sl - tl; i++) {
            for (j = 0; j < tl; j++) {
                if (sc[i + j] != tc[j]) {
                    break;
                }
            }
            if (j == tl) {
                idx.add(i);
            }
        }
        return idx;
    }
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        if (a.compareTo(b) == 0) {
            return find(s, a);
        }
        List<Integer> i1 = find(s, a);
        List<Integer> i2 = find(s, b);
        List<Integer> res = new ArrayList<>();
        int i = 0, j, l1 = i1.size(), l2 = i2.size(), idx1, start, end, idx2, max = s.length() - 1;
        while (i < l1) {
            idx1 = i1.get(i);
            start = Math.max(idx1 - k, 0);
            end = Math.min(idx1 + k, max);
            j = 0;
            while (j < l2) {
                idx2 = i2.get(j);
                if (idx2 >= start && idx2 <= end) {
                    res.add(idx1);
                    break;
                }
                j++;
            }
            i++;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "frtzggff", a = "g", b = "f";
        int k = 1;
        List<Integer> res = s.beautifulIndices(str, a, b, k);
        System.out.println(res);
    }
}
