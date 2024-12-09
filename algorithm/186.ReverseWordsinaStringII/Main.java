import java.util.*;


public class Main {
    public static void reverse(char[] s, int start, int end) {
        int i = start, j = end - 1;
        char tmp;
        while (i < j) {
            tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }
    public static void reverseWords(char[] s) {
        int len = s.length, start = 0;
        reverse(s, 0, len);
        for (int i = 0; i < len; i++) {
            if (s[i] == ' ') {
                reverse(s, start, i);
                start = i + 1;
            }
        }
        reverse(s, start, len);
    }
    public static void main(String[] args) {
        char[] s = new char[]{'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        reverseWords(s);
        System.out.println(Arrays.toString(s));
    }
}
