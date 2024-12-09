import java.util.*;


public class Main {
    public static String decodeString(String s) {
        StringBuilder ret = new StringBuilder();
        StringBuilder num = null;
        int i = 0, count, len = s.length(), left = 0, start = 0, end = 0;
        char cur;
        while (i < len) {
            cur = s.charAt(i);
            if (cur >= 'a' && cur <= 'z') {
                ret.append(cur);
            } else if (cur >= '0' && cur <= '9') {
                if (num == null) {
                    num =  new StringBuilder();
                }
                num.append(cur);
            } else if (cur == '[') {
                count = Integer.parseInt(num.toString());
                num = null;
                left = 1;
                i++;
                start = i;
                while (left != 0) {
                    cur = s.charAt(i);
                    if (cur == '[') {
                        left++;
                    } else if (cur == ']') {
                        left--;
                        if (left == 0) {
                            end = i;
                            break;
                        }
                    }
                    i++;
                }
                String inner = decodeString(s.substring(start, end));
                while (count > 0) {
                    ret.append(inner);
                    count--;
                }
            }
            i++;
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        String ans = decodeString("3[a2[c]]");
        System.out.println(ans);
    }
}
