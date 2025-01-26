import java.util.*;

class Solution {
    private boolean calcNested(char[] chars, Map<Integer, Integer> values, Map<Integer, Integer> ends) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i, len = chars.length, start;
        for (i = 0; i < len; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else if (chars[i] == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                start = stack.pop();
                ends.put(start, i);
                values.put(start, calculator(chars, start + 1, i, values, ends));
            }
        }
        return true;
    }
    private int getNum(char[] chars, int[] cur, int end, Map<Integer, Integer> values, Map<Integer, Integer> ends) {
        boolean positive = true;
        int num = 0;
        while (cur[0] < end && chars[cur[0]] == ' ') {
            cur[0]++;
        }
        if (chars[cur[0]] == '-') {
            positive = false;
            cur[0]++;
        } else if (chars[cur[0]] == '+') {
            cur[0]++;
        }
        if (chars[cur[0]] == '(') {
            int res= values.get(cur[0]);
            cur[0] = ends.get(cur[0]) + 1;
            return res;
        }
        while(cur[0] < end &&
                chars[cur[0]] != '+' &&
                chars[cur[0]] != '-' &&
                chars[cur[0]] != '*' &&
                chars[cur[0]] != '/' &&
                chars[cur[0]] != ' ') {
            num = num * 10 + (chars[cur[0]] - '0');
            cur[0]++;
        }
        if (!positive) {
            num = -num;
        }
        return num;
    }
    private char getOp(char[] chars, int[] cur, int end) {
        while (cur[0] < end && chars[cur[0]] == ' ') {
            cur[0]++;
        }
        char op;
        if (cur[0] < end) {
            op = chars[cur[0]];
            cur[0]++;
        } else {
            op = 0;
        }
        return op;
    }
    private int calcOne(int n1, int n2, char op) {
        return switch (op) {
            case '+' -> n1 + n2;
            case '-' -> n1 - n2;
            case '*' -> n1 * n2;
            case '/' -> n1 / n2;
            default -> 0;
        };
    }
    private int calculator(char[] chars, int start, int end, Map<Integer, Integer> values, Map<Integer, Integer> ends) {
        int[] cur = {start};
        int n1, n2;
        char op1, op2;
        n1 = getNum(chars, cur, end, values, ends);
        if (cur[0] == end) {
            return n1;
        }
        op1 = getOp(chars, cur, end);
        if (op1 == 0) {
            return n1;
        }
        n2 = getNum(chars, cur, end, values, ends);
        if (cur[0] == end) {
            return calcOne(n1, n2, op1);
        }
        while (true) {
            op2 = getOp(chars, cur, end);
            if (op2 == 0) {
                return calcOne(n1, n2, op1);
            }
            if (op1 == '*' || op1 == '/' || op2 == '+' || op2 == '-') {
                n1 = calcOne(n1, n2, op1);
                op1 = op2;
                n2 = getNum(chars, cur, end, values, ends);
            } else {
                n2 = calcOne(n2, getNum(chars, cur, end, values, ends), op2);
            }
            if (cur[0] == end) {
                return calcOne(n1, n2, op1);
            }
        }
    }
    public int calc(String str) {
        char[] chars = str.toCharArray();
        Map<Integer, Integer> values = new HashMap<>();
        Map<Integer, Integer> ends = new HashMap<>();
        if (!calcNested(chars, values, ends)) {
            System.out.println("Parentheses pairing issue");
            return 0;
        }
        return calculator(chars, 0, chars.length, values, ends);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "18*((434-4)/10+39)/12"; // 123
        int res = s.calc(str);
        System.out.println(res);
        str = "9  * (( 5  +  (5 * ( 6 ) / 3 )+    6 / 2)   +  1 )"; //171
        res = s.calc(str);
        System.out.println(res);
    }
}
