import java.util.*;

public class Main {
    private static Map<String, Integer> memo;
    public static void findPres(String[] words, Set<String> wordsSet, Map<String, List<String>> preMap) {
        String pre;
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() <= 1) {
                continue;
            }
            char[] chars = word.toCharArray();
            int l = chars.length;
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < l; j++) {
                    if (j != i) {
                        sb.append(chars[j]);
                    }
                }
                pre = sb.toString();
                if (wordsSet.contains(pre)) {
                    if (preMap.containsKey(word)) {
                        preMap.get(word).add(pre);
                    } else {
                        preMap.put(word, new ArrayList<>(Arrays.asList(pre)));
                    }
                }
                sb.setLength(0);
            }
        }
    }

    public static int dfs(Map<String, List<String>> preMap, String cur, int cnt) {
        if (!preMap.containsKey(cur)) {
            return cnt;
        }
        if (memo.containsKey(cur)) {
            return memo.get(cur) + cnt;
        }
        List<String> pres = preMap.get(cur);
        int result = 1;
        for (String pre : pres) {
            result = Math.max(result, dfs(preMap, pre, cnt + 1));
        }
        memo.put(cur, result - cnt);
        return result;
    }
    public static int longestStrChain(String[] words) {
        Set<String> wordsSet = new HashSet<>(Arrays.asList(words));
        Map<String, List<String>> preMap = new HashMap<>();
        findPres(words, wordsSet, preMap);
        memo = new HashMap<>();
        int result = 1;
        for (String word : preMap.keySet()) {
            result = Math.max(result, dfs(preMap, word, 1));
        }
        return result;
    }
    public static void main(String[] args) {
        String[] words = new String[]{"a","b","ba","bca","bda","bdca"};
        System.out.println(longestStrChain(words));
    }
}
