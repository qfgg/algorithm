import java.util.*;


class Solution {
    public int minAddToMakeValid(String s) {
        char[] chars = s.toCharArray();
        int i, n = chars.length, l = 0, r = 0, min = 0;
        for (i = 0; i < n; i++) {
            if (chars[i] == '(') {
                l++;
            } else if (chars[i] == ')') {
                if (l > 0) {
                    l--;
                } else {
                    min++;
                }
            }
        }
        min += l;
        return min;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "())()(";
        int res = s.minAddToMakeValid(str);
        System.out.println(res);
    }
}
