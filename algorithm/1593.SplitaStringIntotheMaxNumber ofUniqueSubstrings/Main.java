import java.util.*;


public class Main {
    public static void dfs(String s, int start, int end, int count, int[] ans, HashMap<String, Boolean> combination) {
        if (start > end) {
            if (count > ans[0]) {
                ans[0] = count;
            }
            return;
        }
        String cur;
        for (int i = start; i <= end; i++) {
            if (end - start + 1 + count <= ans[0]) {
                return;
            }
            cur = s.substring(start, i + 1);
            if (combination.containsKey(cur)) {
                continue;
            }
            combination.put(cur, true);
            dfs(s, i + 1, end, count + 1, ans, combination);
            combination.remove(cur);
        }
    }
    public static int maxUniqueSplit(String s) {
        HashMap<String, Boolean> combination = new HashMap<>();
        int[] ans = new int[1];
        dfs(s, 0, s.length() - 1, 0, ans, combination);
        return ans[0];
    }

    public static void main(String[] args) {
        String s = "ababab";
        int ans = maxUniqueSplit(s);
        System.out.println(ans);
    }
}
