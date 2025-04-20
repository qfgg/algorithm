import java.util.*;


class Solution {
    private int getIndex(char c) {
        int diff = c - 'a';
        if (diff >= 0 && diff < 26) {
            return diff;
        }
        return c - 'A' + 26;
    }
    public String minWindow(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int[] countS = new int[52];
        int[] countT = new int[52];
        for (char c : tc) {
            countT[getIndex(c)]++;
        }
        int l = 0, r = 0, n = 0, sl = s.length(), tl = t.length(), i, minWidth = Integer.MAX_VALUE, width, minl = -1, minr = -1;
        while (r < sl) {
            i = getIndex(sc[r]);
            if (countS[i] < countT[i]) {
                n++;
            }
            countS[i]++;
            if (n == tl) {
                i = getIndex(sc[l]);
                while (countS[i] > countT[i]) {
                    countS[i]--;
                    l++;
                    i = getIndex(sc[l]);
                }
                width = r - l + 1;
                if (width < minWidth) {
                    minl = l;
                    minr = r;
                    minWidth = width;
                }
            }
            r++;
        }
        if (n < tl) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (minl <= minr) {
            sb.append(sc[minl]);
            minl++;
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "ADOBECODEBANC", t = "ABC";
        String res = s.minWindow(str, t);
        System.out.println(res);
    }
}
