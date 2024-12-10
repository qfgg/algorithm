import java.util.*;

public class Main {
    public static int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Map<String, Integer> dp = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int res, max = 1;
        String pre;
        for (String word : words) {
            if (word.length() == 1) {
                dp.put(word, 1);
            } else {
                char[] chars = word.toCharArray();
                int l = chars.length;
                res = Integer.MIN_VALUE;
                for (int i = 0; i < l; i++) {
                    for (int j = 0; j < l; j++) {
                        if (j != i) {
                            sb.append(chars[j]);
                        }
                    }
                    pre = sb.toString();
                    if (dp.containsKey(pre) && dp.get(pre) > res) {
                        res = dp.get(pre);
                    }
                    sb.setLength(0);
                }
                if (res == Integer.MIN_VALUE) {
                    dp.put(word, 1);
                } else {
                    dp.put(word, res + 1);
                }
                if (res + 1 > max) {
                    max = res + 1;
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        String[] words = new String[]{"xbc","pcxbcf","xb","cxbc","pcxbc"};
        System.out.println(longestStrChain(words));
    }
}