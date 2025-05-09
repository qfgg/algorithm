import java.util.*;


class Solution {

    public String minRemoveToMakeValid(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i, n = chars.length, top = -1, bottom = -1, j = 0;
        int[] idx = new int[n];
        for (i = 0; i < n; i++) {
            if (chars[i] == '(') {
                top++;
                idx[top] = i;
            } else if (chars[i] == ')') {
                if (top == bottom) {
                    top++;
                    bottom++;
                    idx[bottom] = i;
                } else if (top > bottom) {
                    top--;
                }
            }
        }
        if (top > bottom) {
            bottom = top;
        }
        for (i = 0; i < n; i++) {
            if (j <= bottom && i == idx[j]) {
                j++;
                continue;
            }
            sb.append(chars[i]);
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "lee(t(c)o)de)";
        String res = s.minRemoveToMakeValid(str);
        System.out.println(res);
    }
}
