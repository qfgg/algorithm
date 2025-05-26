import java.util.*;


class Solution {
    private int[] manacher(char[] chars) {
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
        }
        return p;
    }
    public String longestPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int n = chars.length, i, max, l, r;
        int[] p = manacher(chars);
        max = 0;
        n = 2 * n + 1;
        for (i = 1; i < n; i++) {
            if (p[i] > p[max]) {
                max = i;
            }
        }
        l = (max - p[max]) / 2;
        r = l + p[max];
        StringBuilder sb = new StringBuilder();
        for (i = l; i < r; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "cbbd";
        String res = s.longestPalindrome(str);
        System.out.println(res);
    }
}
