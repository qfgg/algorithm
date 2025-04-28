import java.util.*;


class Solution {
    private int[] computeKMPTable(char[] wc) {
        int i, wl = wc.length, pre;
        int[] kmp = new int[wl];
        for (i = 1; i < wl; i++) {
            pre = kmp[i - 1];
            while (wc[i] != wc[pre] && pre > 0) {
                pre = kmp[pre - 1];
            }
            if (wc[i] == wc[pre]) {
                pre++;
            }
            kmp[i] = pre;
        }
        return kmp;
    }
    public List<Integer> KMP (String s, String w) {
        char[] sc = s.toCharArray();
        char[] wc = w.toCharArray();
        int i, j, sl = sc.length, wl = wc.length;
        int[] kmp = computeKMPTable(wc);
        List<Integer> res = new ArrayList<>();
        i = 0;
        j = 0;
        while (i < sl && j < wl) {
            if (sc[i] == wc[j]) {
                i++;
                j++;
                if (j == wl) {
                    res.add(i - wl);
                    j = kmp[wl - 1];
                }
            } else {
                if (j > 0) {
                    j = kmp[j - 1];
                } else {
                    i++;
                }
            }
        }
        return res;
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
            return KMP(s, a);
        }
        List<Integer> i1 = KMP(s, a);
        List<Integer> i2 = KMP(s, b);
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
        String str = "aba";
        String a = "a", b = "a";
        List<Integer> res = s.beautifulIndices(str, a, b, 15);
        System.out.println(res);
    }
}
