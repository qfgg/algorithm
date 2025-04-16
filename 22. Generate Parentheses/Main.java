import java.util.*;

class Solution {
    private void gen(int n, int leftUsed, int rightUsed, StringBuilder sb, List<String> list) {
        if (sb.length() == n * 2) {
            list.add(sb.toString());
            return;
        }
        if (leftUsed == rightUsed) {
            sb.append('(');
            gen(n, leftUsed + 1, rightUsed, sb, list);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            if (leftUsed < n) {
                sb.append('(');
                gen(n, leftUsed + 1, rightUsed, sb, list);
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(')');
            gen(n, leftUsed, rightUsed + 1, sb, list);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        gen(n, 0, 0, sb, list);
        return list;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> res = s.generateParenthesis(2);
        System.out.println(res);
    }
}
