import java.util.*;


class Solution {
    private void parse(String exp, List<Integer> nl, List<Character> ol) {
        char[] chars = exp.toCharArray();
        int i, n = chars.length, num = 0;
        for (i = 0; i < n; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                num = num * 10 + (chars[i] - '0');
                if (i == n - 1 || chars[i + 1] < '0' || chars[i + 1] > '9') {
                    nl.add(num);
                    num = 0;
                }
            } else {
                ol.add(chars[i]);
            }
        }
    }
    private int compute(int x, int y, char op) {
        return switch (op) {
            case '+' -> x + y;
            case '-' -> x - y;
            case '*' -> x * y;
            default -> 0;
        };
    }
    private List<Integer> dfs(List<Integer> nl, List<Character> ol, int start, int end) {
        if (start == end) {
            return new ArrayList<>(List.of(nl.get(start)));
        }
        if (start + 1 == end) {
            return new ArrayList<>(List.of(compute(nl.get(start), nl.get(end), ol.get(start))));
        }
        List<Integer> res = new ArrayList<>(), left, right;
        int ln, rn, m, n;
        for (int i = start; i < end; i++) {
            left = dfs(nl, ol, start, i);
            right = dfs(nl, ol, i + 1, end);
            ln = left.size();
            rn = right.size();
            for (m = 0; m < ln; m++) {
                for (n = 0; n < rn; n++) {
                    res.add(compute(left.get(m), right.get(n), ol.get(i)));
                }
            }
        }
        return res;
    }
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> nl = new ArrayList<>();
        List<Character> ol = new ArrayList<>();
        parse(expression, nl, ol);
        if (nl.size() == 1) {
            List<Integer> res = new ArrayList<>();
            res.add(nl.get(0));
            return res;
        }
        return dfs(nl, ol, 0, nl.size() - 1);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String expression = "2*3-4*5";
        List<Integer> res = s.diffWaysToCompute(expression);
        System.out.println(res);
    }
}
