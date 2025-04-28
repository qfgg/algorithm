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
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "ABC ABCDABCDABD ABCDABCDABDE";
        String word = "ABCDABD";
        List<Integer> res = s.KMP(str, word);
        System.out.println(res);
    }
}
