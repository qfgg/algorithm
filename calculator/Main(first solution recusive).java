import java.util.*;

class Solution {
    private int findRightParentheses(char[] chars, int cur, int end) {
        int count = 0;
        while (cur < end) {
            if (chars[cur] == '(') {
                count++;
            } else if (chars[cur] == ')') {
                count--;
                if (count == 0) {
                    return cur;
                }
            }
            cur++;
        }
        return -1;
    }
    private int getNum(char[] chars, int[] cur, int end) {
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
            int right = findRightParentheses(chars, cur[0], end);
            int res = calculator(chars, cur[0] + 1, right);
            cur[0] = right + 1;
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
    private int calculator(char[] chars, int start, int end) {
        int[] cur = {start};
        int n1, n2;
        char op1, op2;
        n1 = getNum(chars, cur, end);
        if (cur[0] == end) {
            return n1;
        }
        op1 = getOp(chars, cur, end);
        if (op1 == 0) {
            return n1;
        }
        n2 = getNum(chars, cur, end);
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
                n2 = getNum(chars, cur, end);
            } else {
                n2 = calcOne(n2, getNum(chars, cur, end), op2);
            }
            if (cur[0] == end) {
                return calcOne(n1, n2, op1);
            }
        }
    }
    public int calc(String str) {
        char[] chars = str.toCharArray();
        return calculator(chars, 0, chars.length);
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
