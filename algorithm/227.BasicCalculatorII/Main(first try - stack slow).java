import java.util.*;

public class Main {
    public static int calc(char op, int n1, int n2) {
        if (op == '+') {
            return n1 + n2;
        }
        if (op == '-') {
            return n1 - n2;
        }
        if (op == '*') {
            return n1 * n2;
        }
        return n1 / n2;
    }
    public static int calculate(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int len = s.length(), n = 0;
        char c, pre;
        for (int i = 0; i < len; i++) {
            c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                n = n * 10 + (c - '0');
            } else if (c == '*' || c == '/') {
                pre = ops.isEmpty() ? 0 : ops.peek();
                if (pre == '*' || pre == '/') {
                    n = calc(ops.pop(), nums.pop(), n);
                }
                nums.push(n);
                n = 0;
                ops.push(c);
            } else if (c == '+' || c == '-') {
                pre = ops.isEmpty() ? 0 : ops.peek();
                if (pre != 0) {
                    while (!ops.isEmpty()) {
                        n = calc(ops.pop(), nums.pop(), n);
                    }
                }
                nums.push(n);
                n = 0;
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            n = calc(ops.pop(), nums.pop(), n);
        }
        return n;
    }
    public static void main(String[] args) {
        String s = "1*2-3/4+5*6-7*8+9/10";
        int ans = calculate(s);
        System.out.println(ans);
    }
}
