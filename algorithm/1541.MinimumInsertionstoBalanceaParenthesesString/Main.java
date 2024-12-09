import java.util.*;


public class Main {
    public static int minInsertions(String s) {
        char[] str = s.toCharArray();
        int len = str.length, add = 0, left = 0;
        int i = 0;
        while (i < len) {
            if (str[i] == '(') {
                left++;
                i++;
            } else {
                if (i == len - 1 || str[i + 1] != ')') {
                    add++;
                    i++;
                } else {
                    i = i + 2;
                }
                if (left == 0) {
                    add++;
                } else {
                    left--;
                }
            }
        }
        if (left > 0) {
            add += 2 * left;
        }
        return add;
    }
    public static void main(String[] args) {
        String s = "))())(";
        int ans = minInsertions(s);
        System.out.println(ans);
    }
}
