import java.util.*;


class Solution {
    long eval (String num, char[] op) {
        List<String> exp = new ArrayList<>();
        ArrayDeque<Long> nums = new ArrayDeque<>();
        ArrayDeque<Character> ops = new ArrayDeque<>();
        int n = num.length(), i, start = 0;
        for (i = 0; i < n - 1; i++) {
            if (op[i] == '+' || op[i] == '-' || op[i] == '*') {
                exp.add(num.substring(start, i + 1));
                exp.add(String.valueOf(op[i]));
                start = i + 1;
            }
        }
        exp.add(num.substring(start, n));
        long cur, top;
        char curOp, preOp;
        n = exp.size();
        for (i = 0; i < n; i++) {
            if (i % 2 == 0) {
                cur = Long.valueOf(exp.get(i));
                nums.push(cur);
            } else {
                curOp = exp.get(i).charAt(0);
                if (curOp == '*') {
                    top = nums.pop();
                    nums.push(top * Long.valueOf(exp.get(i + 1)));
                    i++;
                } else {
                    if (!ops.isEmpty()) {
                        preOp = ops.pop();
                        top = nums.pop();
                        if (preOp == '+') {
                            nums.push(nums.pop() + top);
                        } else {
                            nums.push(nums.pop() - top);
                        }
                    }
                    ops.push(curOp);
                }
            }
        }
        if (ops.isEmpty()) {
            return nums.pop();
        }
        top = nums.pop();
        curOp = ops.pop();
        return curOp == '*' ?
                nums.pop() * top : curOp == '+' ?
                nums.pop() + top : nums.pop() - top;
    }
    String getStr(char[] ch, char[] op, StringBuilder sb) {
        int n = ch.length, i;
        for (i = 0; i < n; i++) {
            sb.append(ch[i]);
            if (i < n - 1 && (op[i] == '+' || op[i] == '-' || op[i] == '*')) {
                sb.append(op[i]);
            }
        }
        String res = sb.toString();
        sb.setLength(0);
        return res;
    }
    void dfs(String num, char[] ch, char[] op, int pos, int end, int target, List<String> res, boolean isLeadingZero, StringBuilder sb) {
        if (pos == end) {
            if (eval(num, op) == target) {
                res.add(getStr(ch, op, sb));
            }
            return;
        }
        if (!isLeadingZero) {
            dfs(num, ch, op, pos + 1, end, target, res, isLeadingZero, sb);
        }
        op[pos] = '+';
        dfs(num, ch, op, pos + 1, end, target, res, ch[pos + 1] == '0', sb);
        op[pos] = '-';
        dfs(num, ch, op, pos + 1, end, target, res, ch[pos + 1] == '0', sb);
        op[pos] = '*';
        dfs(num, ch, op, pos + 1, end, target, res, ch[pos + 1] == '0', sb);
        op[pos] = 0;
    }
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        char[] ch = num.toCharArray();
        int n = ch.length;
        char[] op = new char[n - 1];
        StringBuilder sb = new StringBuilder();
        dfs(num, ch, op, 0, n - 1, target, res, ch[0] == '0', sb);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String num = "123";
        int target = 6;
        List<String> res = s.addOperators(num, target);
        System.out.println(res);
    }
}
