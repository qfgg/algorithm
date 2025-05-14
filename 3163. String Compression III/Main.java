import java.util.*;


class Solution {
    public String compressedString(String word) {
        int i, n = word.length(), count = 0;
        char next = word.charAt(0), cur = next;
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < n; i++) {
            count++;
            if (i < n - 1) {
                next = word.charAt(i + 1);
            }
            if (i == n - 1 || cur != next || count == 9) {
                sb.append((char)('0' + count));
                sb.append(cur);
                count = 0;
                cur = next;
            }
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "aaaaaaaaaaaaaabb";
        String res = s.compressedString(str);
        System.out.println(res);
    }
}
