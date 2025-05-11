import java.util.*;


class Solution {
    public String addSpaces(String s, int[] spaces) {
        char[] chars = s.toCharArray();
        int count = 0, i, j = 0, n = chars.length, spn = spaces.length;
        char[] res = new char[n + spn];
        for (i = 0; i < n; i++) {
            if (j < spn && i == spaces[j]) {
                res[i + count] = ' ';
                count++;
                j++;
            }
            res[i + count] = chars[i];
        }
        return new String(res);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "LeetcodeHelpsMeLearn";
        int[] spaces = new int[]{0,8,13,15};
        String res = s.addSpaces(str, spaces);
        System.out.println(res);
    }
}
