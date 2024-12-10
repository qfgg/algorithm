import java.util.*;


public class Main {
    public static void dfs(char[] chars, int start, List<Character> combination, List<String> ret) {
        if (start == chars.length) {
            StringBuilder sb = new StringBuilder();
            for (Character ch : combination) {
                sb.append(ch);
            }
            ret.add(sb.toString());
            return;
        }
        if (chars[start] != '{') {
            combination.add(chars[start]);
            dfs(chars, start + 1, combination, ret);
            combination.remove(combination.size() - 1);
        } else {
            List<Character> letters = new ArrayList<>();
            int i = start + 1;
            while (chars[i] != '}') {
                if (chars[i] != ',') {
                    letters.add(chars[i]);
                }
                i++;
            }
            for (int j = 0; j < letters.size(); j++) {
                combination.add(letters.get(j));
                dfs(chars, i + 1, combination, ret);
                combination.remove(combination.size() - 1);
            }
        }
    }
    public static String[] expand(String s) {
        List<String> ret = new ArrayList<>();
        List<Character> combination = new ArrayList<>();
        char[] chars = s.toCharArray();
        dfs(chars, 0, combination, ret);
        return ret.toArray(new String[0]);
    }
    public static void main(String args[]) {
        String[] ans = expand("{a,b}c{d,e}f");
        System.out.println(Arrays.toString(ans));
    }
}
