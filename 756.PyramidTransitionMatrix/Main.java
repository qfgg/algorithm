import java.util.*;


public class Main {
    public static boolean dfs(int[] b, int next, int i, int j, int n, int[] state, Map<Integer, List<Integer>> validMap) {
        if (j == i) {
            if (i == n - 1) {
                return true;
            }
            if (j > 0) {
                state[j - 1] = state[j - 1] * 10 + next;
            }
            if (dfs(b, 0, i + 1, 0, n, state, validMap)) {
                return true;
            }
            if (j > 0) {
                state[j - 1] = state[j - 1] / 10;
            }
        } else {
            int cur = j == 0 ? b[i - 1] * 10 + b[i] : (state[j - 1] % 10) * 10 + next;
            if (!validMap.containsKey(cur)) {
                return false;
            }
            List<Integer> valids = validMap.get(cur);
            for (Integer v : valids) {
                if (j > 0) {
                    state[j - 1] = state[j - 1] * 10 + next;
                }
                if (dfs(b, v, i, j + 1, n, state, validMap)) {
                    return true;
                }
                if (j > 0) {
                    state[j - 1] = state[j - 1] / 10;
                }
            }
        }
        return false;
    }
    public static boolean pyramidTransition(String bottom, List<String> allowed) {
        int key, val, prefix = (bottom.charAt(0) - 'A' + 1) * 10 + (bottom.charAt(1) - 'A' + 1);
        Map<Integer, List<Integer>> validMap = new HashMap<>();
        int bn = bottom.length(), i,  start = 0;
        for (String al : allowed) {
            key = (al.charAt(0) - 'A' + 1) * 10 + (al.charAt(1) - 'A' + 1);
            if (bottom.length() == 2 && key == prefix) {
                return true;
            }
            val = al.charAt(2) - 'A' + 1;
            if (key == prefix) {
                start = key;
            }
            validMap.putIfAbsent(key, new ArrayList<>());
            validMap.get(key).add(val);
        }
        if (start == 0) {
            return false;
        }
        int[] b = new int[bn];
        int[] state = new int[bn - 1];
        for (i = 0; i < bn; i++) {
            b[i] = bottom.charAt(i) - 'A' + 1;
        }
        List<Integer> next = validMap.get(start);
        for (Integer n : next) {
            Arrays.fill(state, 0);
            state[0] = n;
            if (dfs(b, 0, 2, 0, bn, state, validMap)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String bottom = "AAAA";
        List<String> allowed = new ArrayList<>(Arrays.asList(new String[]{
                "AAB","AAC","BCD","BBE","DEF"
        }));
        boolean res = pyramidTransition(bottom, allowed);
        System.out.println(res);
    }
}
