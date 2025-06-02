import java.util.*;


class Solution {
    int check(int[] d, int start, int end) {
        for (int i = start; i <= end; i++) {
            if ((i > 0 && d[i] == d[i - 1]) || (i > 1 && d[i] == d[i - 2])) {
                return i;
            }
        }
        return -1;
    }
    public String smallestBeautifulString(String s, int k) {
        char[] sc = s.toCharArray();
        int sl = sc.length, i, invalidPos, leftmostChanged = Integer.MAX_VALUE;
        int[] d = new int[sl];
        for (i = 0; i < sl; i++) {
            d[i] = sc[i] - 'a' + 1;
        }
        i = sl - 1;
        while (true) {
            d[i]++;
            leftmostChanged = Math.min(leftmostChanged, i);
            while (d[i] == k + 1) {
                // max len reached
                if (i == 0) {
                    return "";
                }
                d[i] = 1;
                d[i - 1]++;
                leftmostChanged = Math.min(leftmostChanged, i - 1);
                i--;
            }
            invalidPos = check(d, leftmostChanged, sl - 1);
            if (invalidPos == -1) {
                break;
            }
            i = invalidPos;
        }
        i = leftmostChanged;
        while (i < sl) {
            sc[i] = (char)(d[i] + 'a' - 1);
            i++;
        }
        return new String(sc);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "ponponp";
        int k = 16;
        String res = s.smallestBeautifulString(str, k);
        System.out.println(res);
    }
}
