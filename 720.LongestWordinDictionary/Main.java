import java.util.*;

class Trie {
    Trie[] next;
    boolean isEnd;
    Trie() {
        next = new Trie[26];
        isEnd = false;
    }
    String insertValidWord(String word, String longestWord) {
        int n = word.length(), i, idx, maxLen = longestWord.length();
        Trie node = this;
        for (i = 0; i < n; i++) {
            idx = word.charAt(i) - 'a';
            if (node.next[idx] == null && (n == 1 || (i == n - 1 && node.isEnd))) {
                node.next[idx] = new Trie();
            }
            if (node.next[idx] == null) {
                break;
            }
            node = node.next[idx];
        }
        if (i == n) {
            node.isEnd = true;
            if ((n > maxLen) || (n == maxLen && word.compareTo(longestWord) < 0)) {
                return word;
            }
        }
        return "";
    }
}
public class Main {
    public static String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        String longest = "", candidate;
        Trie t = new Trie();
        for (String w : words) {
            candidate = t.insertValidWord(w, longest);
            if (!candidate.isEmpty()) {
                longest = candidate;
            }
        }
        return longest;
    }
    public static void main(String[] args) {
        String[] words = new String[]{"e","en","eng","engl","engli","englis","english","h","hi","his","hist","histo","histor","history"};
        String res = longestWord(words);
        System.out.println(res); //english
    }
}
