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
    private boolean isBeautiful(int idx1, List<Integer> i2, int k, int max) {
        int l = 0, r = i2.size(), m, idx2, start = Math.max(idx1 - k, 0), end = Math.min(idx1 + k, max);
        while (l < r) {
            m = (l + r) / 2;
            idx2 = i2.get(m);
            if (start <= idx2 && idx2 <= end) {
                return true;
            }
            if (idx2 < start) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return false;
    }
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        if (a.compareTo(b) == 0) {
            return find(s, a);
        }
        List<Integer> i1 = find(s, a);
        List<Integer> i2 = find(s, b);
        List<Integer> res = new ArrayList<>();
        int idx1, l1 = i1.size();
        for (int i = 0; i < l1; i++) {
            idx1 = i1.get(i);
            if (isBeautiful(idx1, i2, k, s.length() - 1)) {
                res.add(idx1);
            }
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
