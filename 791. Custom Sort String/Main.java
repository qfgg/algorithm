import java.util.*;


class Solution {
    public String customSortString(String order, String s) {
        int ol = order.length(), sl = s.length(), i, num;
        int[] cnt = new int[26];
        Set<Character> extraSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        char ch;
        for (i = 0; i < sl; i++) {
            ch = s.charAt(i);
            cnt[ch - 'a']++;
            extraSet.add(ch);
        }
        for (i = 0; i < ol; i++) {
            ch = order.charAt(i);
            num = cnt[ch - 'a'];
            if (num > 0) {
                sb.repeat(ch, num);
            }
            extraSet.remove(ch);
        }
        for (Character c : extraSet) {
            sb.repeat(c, cnt[c - 'a']);
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String order = "cba", str = "abcd";
        String res = s.customSortString(order, str);
        System.out.println(res);
    }
}
