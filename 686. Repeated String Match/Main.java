import java.util.*;


class Solution {
    int[] computeKMP(char[] ch) {
        int n = ch.length, i, idx;
        int[] kmp = new int[n];
        for (i = 1; i < n; i++) {
            idx = kmp[i - 1];
            while (ch[i] != ch[idx] && idx > 0) {
                idx = kmp[idx - 1];
            }
            if (ch[i] == ch[idx]) {
                idx++;
            }
            kmp[i] = idx;
        }
        return kmp;
    }
    public int repeatedStringMatch(String a, String b) {
        char[] ac = a.toCharArray();
        char[] bc = b.toCharArray();
        int[] kmp = computeKMP(bc);
        int times = 0, i = 0, j = 0, alen = ac.length, blen = bc.length;
        HashSet<List<Integer>> idx = new HashSet<>();
        while (i < alen && j < blen) {
            List<Integer> ij = new ArrayList<>(List.of(i, j));
            if (idx.contains(ij)) {
                return -1;
            }
            idx.add(ij);
            if (ac[i] == bc[j]) {
                i++;
                j++;
                if (j == blen) {
                    times++;
                    break;
                }
                if (i == alen) {
                    i = 0;
                    times++;
                }
            } else {
                if (j > 0) {
                    j = kmp[j - 1];
                } else {
                    i++;
                }
            }
        }
        return times == 0 ? -1 : times;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String a = "abcd", b = "cdabcdacdabcda";
        int res = s.repeatedStringMatch(a, b);
        System.out.println(res);
    }
}
