import java.util.*;


class Solution {
    private int calc(int x, int y, char op) {
        if (op == '+') {
            return x + y;
        }
        if (op == '-') {
            return x - y;
        }
        return 0;
    }
    public int calculate(String s) {
        int i, n = s.length(), oTop = -1, nTop = -1, curNum = 0, result = 0, tmp;
        char cur;
        boolean isPreLeft = false;
        char[] op = new char[n];
        int[] num = new int[n];
        for (i = 0; i < n; i++) {
            cur = s.charAt(i);
            if (cur == '(') {
                oTop++;
                op[oTop] = '(';
                isPreLeft = true;
            } else if (cur - '0' >= 0 && cur - '9' <= 0) {
                curNum = curNum * 10 + cur - '0';
                if (i == n - 1 || s.charAt(i + 1) - '0' < 0 || s.charAt(i + 1) - '9' > 0) {
                    nTop++;
                    num[nTop] = curNum;
                    curNum = 0;
                }
                isPreLeft = false;
            } else if (cur == '+' || cur == '-') {
                if (isPreLeft || nTop < 0) {
                    nTop++;
                    num[nTop] = 0;
                }
                if (oTop >= 0 && (op[oTop] == '+' || op[oTop] == '-')) {
                    tmp = calc(num[nTop - 1], num[nTop], op[oTop]);
                    nTop--;
                    num[nTop] = tmp;
                    op[oTop] = cur;
                } else {
                    oTop++;
                    op[oTop] = cur;
                }
                isPreLeft = false;
            } else if (cur == ')') {
                while (op[oTop] != '(') {
                    tmp = calc(num[nTop - 1], num[nTop], op[oTop]);
                    nTop--;
                    num[nTop] = tmp;
                    oTop--;
                }
                oTop--;
                isPreLeft = false;
            }
        }
        if (oTop >= 0) {
            result = calc(num[nTop - 1], num[nTop], op[oTop]);
        } else if (nTop >= 0) {
            result = num[nTop];
        }
        return result;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
//        String str = "   (1+(4+ 5   +2) -3)+(  6+8)";
        String str = "-(1     -2)";
        int res = s.calculate(str);
        System.out.println(res);
    }
}
