import java.util.*;


public class Main {
    public static void dfs(String digits, char[][] keyMap, int start, StringBuilder sb, List<String> ans) {
        if (start == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        int idx = digits.charAt(start) - '0';
        char[] chars = keyMap[idx];
        for (char c : chars) {
            sb.append(c);
            dfs(digits, keyMap, start + 1, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    public static List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }
        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();
        char[][] keyMap = new char[][]{{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        dfs(digits, keyMap, 0, sb, ans);
        return ans;
    }
    public static void main(String[] args) {
        String digits = "23";
        List<String> ans = letterCombinations(digits);
        System.out.println(Arrays.toString(ans.toArray()));
    }
}