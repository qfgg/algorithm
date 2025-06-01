import java.util.*;


class Solution {
    void countWords(String[] words, HashMap<String, Integer> wordTimes) {
        int i, wn = words.length;
        for (i = 0; i < wn; i++) {
            wordTimes.put(words[i], wordTimes.getOrDefault(words[i], 0) + 1);
        }
    }
    void find(String s, int wn, int wlen, int start, int w, List<Integer> res, HashMap<String, Integer> wordTimes) {
        if (s.length() - start < w) {
            return;
        }
        int l = start, r = start, end = s.length(), count = 0, more = 0;
        HashMap<String, Integer> used = new HashMap<>();
        String cur;
        while (r - l < w) {
            r = r + wlen;
            cur = s.substring(r - wlen, r);
            if (wordTimes.containsKey(cur)) {
                used.put(cur, used.getOrDefault(cur, 0) + 1);
                if (used.get(cur) > wordTimes.get(cur)) {
                    more++;
                } else {
                    count++;
                }
            }
        }
        if (count == wn && more == 0) {
            res.add(l);
        }
        while (r + wlen <= end) {
            r = r + wlen;
            cur = s.substring(r - wlen, r);
            if (wordTimes.containsKey(cur)) {
                used.put(cur, used.getOrDefault(cur, 0) + 1);
                if (used.get(cur) > wordTimes.get(cur)) {
                    more++;
                } else {
                    count++;
                }
            }
            l = l + wlen;
            cur = s.substring(l - wlen, l);
            if (wordTimes.containsKey(cur)) {
                used.put(cur, used.getOrDefault(cur, 0) - 1);
                if (used.get(cur) >= wordTimes.get(cur)) {
                    more--;
                } else {
                    count--;
                }
            }
            if (count == wn && more == 0) {
                res.add(l);
            }
        }
    }
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wn = words.length, wlen = words[0].length(), w = wn * wlen;
        HashMap<String, Integer> wordTimes = new HashMap<>();
        countWords(words, wordTimes);
        for (int i = 0; i < wlen; i++) {
            find(s, wn, wlen, i, w, res, wordTimes);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "ababababab";
        String[] words = new String[]{"ababa","babab"};
        List<Integer> res = s.findSubstring(str, words);
        System.out.println(res);
    }
}
