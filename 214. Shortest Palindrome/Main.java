import java.util.*;


class Solution {
    private int[] manacher(char[] chars, int[] r) {
        int n1 = chars.length, n2 = n1 * 2 + 1, i, j, rbound = -1, center = -1, left;
        // preprocess string, "aacecaaa" -> "#a#a#c#e#c#a#a#a#"
        char[] ch = new char[n2];
        int[] p = new int[n2];
        for (i = 0; i < n2; i++) {
            ch[i] = i % 2 == 0 ? '#' : chars[(i - 1) / 2];
        }
        for (i = 0; i < n2; i++) {
            if (i > rbound) {
                p[i] = 0;
                j = 1;
                while (i - j >= 0 && i + j < n2 && ch[i - j] == ch[i + j]) {
                    p[i]++;
                    j++;
                }
                center = i;
                rbound = i + p[i];
            } else {
                left = center * 2 - i;
                if (i + p[left] < center + p[center]) {
                    p[i] = p[left];
                } else if (i + p[left] > center + p[center]) {
                    p[i] = center + p[center] - i;
                } else {
                    p[i] = p[left];
                    j = p[left] + 1;
                    while (i - j >= 0 && i + j < n2 && ch[i - j] == ch[i + j]) {
                        p[i]++;
                        j++;
                    }
                    if (p[i] > p[left]) {
                        center = i;
                        rbound = i + p[i];
                    }
                }
            }
            if (p[i] == i) {
                r[0] = Math.max(r[0], p[i]- 1);
            }
        }
        return p;
    }
    public String shortestPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int n = chars.length, i;
        int[] r = new int[1];
        manacher(chars, r);
        StringBuilder sb = new StringBuilder();
        for (i = n - 1; i > r[0]; i--) {
            sb.append(chars[i]);
        }
        sb.append(chars);
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "aacecaaa";
        String res = s.shortestPalindrome(str);
        System.out.println(res);
    }
}
