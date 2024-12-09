import java.util.*;


public class Main {
    public static int evalRPN(String[] tokens) {
        int n = tokens.length, top1, top2;
        Deque<Integer> s = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (tokens[i].equals("+")) {
                top2 = s.pop();
                top1 = s.pop();
                s.push(top1 + top2);
            } else if (tokens[i].equals("-")) {
                top2 = s.pop();
                top1 = s.pop();
                s.push(top1 - top2);
            } else if (tokens[i].equals("*")) {
                top2 = s.pop();
                top1 = s.pop();
                s.push(top1 * top2);
            } else if (tokens[i].equals("/")) {
                top2 = s.pop();
                top1 = s.pop();
                s.push(top1 / top2);
            } else {
                s.push(Integer.parseInt(tokens[i]));
            }
        }
        return s.pop();
    }
    public static void main(String[] args) {
        String[] tokens = new String[]{"4","13","5","/","+"};
        int res = evalRPN(tokens);
        System.out.println(res);
    }
}
