import java.util.*;


public class Main {
    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int l = 0, r = 0, rmax = chars.length, max = 0;
        Set<Character> charset = new HashSet<>();
        while (r < rmax) {
            if (charset.contains(chars[r])) {
                charset.remove(chars[l]);
                l++;
            } else {
                charset.add(chars[r]);
                if (r - l + 1 > max) {
                    max = r - l + 1;
                }
                r++;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        String s = "pwwkew";
        int ans = lengthOfLongestSubstring(s);
        System.out.println(ans);
    }
}
