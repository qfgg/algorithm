import java.util.*;


class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> stk = new ArrayDeque<>();
        char[] ch = s.toCharArray();
        int n = ch.length, i, top;
        for (i = 0; i < n; i++) {
            if (ch[i] == '(') {
                stk.push(-1);
            } else {
                top = stk.pop();
                if (top == -1) {
                    if (stk.isEmpty() || stk.peek() == -1) {
                        stk.push(1);
                    } else {
                        stk.push(stk.pop() + 1);
                    }
                } else if (top > 0) {
                    if (stk.peek() == -1) {
                        stk.pop();
                        if (!stk.isEmpty() && stk.peek() > 0) {
                            stk.push(stk.pop() + top * 2);
                        } else {
                            stk.push(top * 2);
                        }
                    } else {
                        stk.push(stk.pop() + top);
                    }
                }
            }
        }
        return stk.peek();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "(())(())";
        int res = s.scoreOfParentheses(str);
        System.out.println(res);
    }
}
