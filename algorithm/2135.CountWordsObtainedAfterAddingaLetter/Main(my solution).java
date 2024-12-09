import java.util.*;

public class Main {
    public static String getKey(String s, char exclude) {
        StringBuilder sb = new StringBuilder();
        Set<Character> charSet = new HashSet<>();
        int l = s.length(), i;
        for (i = 0; i < l; i++) {
            charSet.add(s.charAt(i));
        }
        char cur = 'a';
        while (cur <= 'z') {
            if (cur != exclude && charSet.contains(cur)) {
                sb.append(cur);
            }
            cur = (char)(cur + 1);
        }
        return sb.toString();
    }
    public static int wordCount(String[] startWords, String[] targetWords) {
        Set<String> startSet = new HashSet<>();
        for (String sw : startWords) {
            startSet.add(getKey(sw, '\u0000'));
        }
        int cnt = 0, l, i;
        for (String tw : targetWords) {
            l = tw.length();
            for (i = 0; i < l; i++) {
                if (startSet.contains(getKey(tw, tw.charAt(i)))) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        String[] startWords = new String[]{"ant","act","tack"};
        String[] targetWords = new String[]{"tack","act","acti"};
        int res = wordCount(startWords, targetWords);
        System.out.println(res);
    }
}
