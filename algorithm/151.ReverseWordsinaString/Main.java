import java.util.*;

class Main {
    public static String reverseWords(String s) {
        StringBuilder ret = new StringBuilder("");
        int start = s.length() - 1;
        int end;
        while (true) {
            end = start;
            while (end >= 0 && s.charAt(end) == ' ') {
                end--;
            }
            if (end < 0) {
                break;
            }
            start = end;
            while (start >= 0 && s.charAt(start) != ' ') {
                start--;
            }
            String word = s.substring(start + 1, end + 1);
            if (!ret.isEmpty()) {
                ret.append(" ");
            }
            ret.append(word);
        }
        return ret.toString();
    }
    public static void main(String[] args) {
        String s = "   a good   example ";
        System.out.println(reverseWords(s));
    }
}
