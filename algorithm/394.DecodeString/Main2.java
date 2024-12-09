import java.util.*;


public class Main {
    public static String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder repeat = new StringBuilder();
        Stack<Integer> count = new Stack<>();
        Stack<String> pre = new Stack<>();
        int i = 0, len = s.length(), num = 0, curCount;
        char cur;
        String inStr, curPre;
        while (i < len) {
            cur = s.charAt(i);
            if (cur >= 'a' && cur <= 'z') {
                sb.append(cur);
            } else if (Character.isDigit(cur)) {
                num = num * 10 + (cur - '0');
            } else if (cur == '[') {
                count.push(num);
                num = 0;
                pre.push(sb.toString());
                sb.setLength(0);
            } else if (cur == ']') {
                inStr = sb.toString();
                curCount = count.pop();
                while (curCount > 0) {
                    repeat.append(inStr);
                    curCount--;
                }
                curPre = pre.pop();
                sb.setLength(0);
                sb.append(curPre);
                sb.append(repeat);
                repeat.setLength(0);
            }
            i++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String ans = decodeString("3[a2[c]]");
        System.out.println(ans);
    }
}
