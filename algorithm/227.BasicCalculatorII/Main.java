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
        if (op == '/') {
            return n1 / n2;
        }
        return 0;
    }
    public static int calculate(String s) {
        int len = s.length(), n = 0, n1 = 0, n0 = 0;
        char c, preOp = 0, op0 = 0;
        for (int i = 0; i < len; i++) {
            c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                n = n * 10 + (c - '0');
            } else if (c == '*' || c == '/') {
                if (preOp == '+' || preOp == '-') {
                    op0 = preOp;
                    n0 = n1;
                } else if (preOp == '*' || preOp == '/') {
                    n = calc(preOp, n1, n);
                }
                preOp = c;
                n1 = n;
                n = 0;
            } else if (c == '+' || c == '-') {
                if (preOp != 0) {
                    n = calc(preOp, n1, n);
                }
                if (op0 != 0) {
                    n = calc(op0, n0, n);
                    op0 = 0;
                }
                preOp = c;
                n1 = n;
                n = 0;
            }
        }
        n = preOp != 0 ? calc(preOp, n1, n) : n;
        return op0 != 0 ? calc(op0, n0, n) : n;
    }
    public static void main(String[] args) {
        String s = "1*2-3/4+5*6-7*8+9/10";
        int ans = calculate(s);
        System.out.println(ans);
    }
}
