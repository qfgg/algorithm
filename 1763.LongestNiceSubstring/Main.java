import java.util.*;


public class Main {
    public static void findNS(char[] str, int start, int end, int[] ans) {
        Set<Character> alone = new HashSet<>();
        Set<Character> dup = new HashSet<>();
        int s = start, e = start;
        char partner;
        while (e < end) {
            partner = str[e] >= 'a' && str[e] <= 'z' ?
                    Character.toUpperCase(str[e]) : Character.toLowerCase(str[e]);
            if (!dup.contains(str[e])) {
                if (!alone.contains(partner)) {
                    alone.add(str[e]);
                } else {
                    alone.remove(partner);
                    dup.add(str[e]);
                    dup.add(partner);
                }
            }
            e++;
        }
        if (alone.isEmpty()) {
            if (ans[0] == -1 ||
                    (e - s > ans[1] - ans[0]) ||
                    (s < ans[0] && ans[1] - ans[0] == e - s)) {
                ans[0] = s;
                ans[1] = e;
            }
        } else {
            s = start;
            e = start;
            while (e <= end) {
                if (e == end || alone.contains(str[e])) {
                    if (e - s > 1) {
                        findNS(str, s, e, ans);
                    }
                    s = e + 1;
                }
                e++;
            }
        }
    }
    public static String longestNiceSubstring(String s) {
        int len = s.length();
        if (len <= 1) {
            return "";
        }
        char[] chars = s.toCharArray();
        int[] ans = new int[]{-1, -1};
        findNS(chars, 0, len, ans);
        if (ans[0] == -1) {
            return "";
        }
        return s.substring(ans[0], ans[1]);
    }
    public static void main(String[] args) {
        String s = "HkhBubUYy";
        String ans = longestNiceSubstring(s);
        System.out.println(ans); // "aAa"
    }
}
