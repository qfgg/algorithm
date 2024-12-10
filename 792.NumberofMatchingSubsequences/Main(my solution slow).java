import java.util.*;

public class Main {
    public static int numMatchingSubseq(String s, String[] words) {
        int cnt = 0, sl = s.length(), wn = words.length, i, j;
        Map<String, Integer> wordCnt = new HashMap<>();
        for (i = 0; i < wn; i++) {
            if (wordCnt.containsKey(words[i])) {
                wordCnt.put(words[i], wordCnt.get(words[i]) + 1);
            } else {
                wordCnt.put(words[i], 1);
            }
        }
        wn = wordCnt.size();
        String[] uniqueWords = wordCnt.keySet().toArray(new String[wordCnt.size()]);
        int[] wi = new int[wn];
        char c;
        i = 0;
        while (i < sl) {
            c = s.charAt(i);
            for (j = 0; j < wn; j++) {
                if (wi[j] < uniqueWords[j].length() && c == uniqueWords[j].charAt(wi[j])) {
                    wi[j]++;
                    if (wi[j] == uniqueWords[j].length()) {
                        cnt += wordCnt.get(uniqueWords[j]);
                    }
                }
            }
            i++;
        }
        return cnt;
    }
    public static void main(String[] args) {
        String s = "abcde";
        String[] words = new String[]{"a","bb","acd","ace"};
        int ans = numMatchingSubseq(s, words);
        System.out.println(ans);
    }
}
