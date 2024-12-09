import java.util.*;

class Trie {
    Trie[] children;
    boolean isEnd;
    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }
    public void insert(String word) {
        int n = word.length(), i;
        char c;
        Trie[] ch = children;
        for (i = 0; i < n; i++) {
            c = word.charAt(i);
            if (ch[c - 'a'] == null) {
                ch[c - 'a'] = new Trie();
            }
            if (i == n - 1) {
                ch[c - 'a'].isEnd = true;
            }
            ch = ch[c - 'a'].children;
        }
    }

    public boolean searchChildren(String word, int start, Trie[] ch) {
        int n = word.length(), i;
        char c;
        for (i = start; i < n; i++) {
            c = word.charAt(i);
            if (c == '.') {
                for (Trie child : ch) {
                    if (child != null &&
                            ((i == n - 1 && child.isEnd) ||
                            searchChildren(word, i + 1, child.children))) {
                        return true;
                    }
                }
                return false;
            } else {
                if (ch[c - 'a'] == null) {
                    return false;
                }
                if (i == n - 1 && ch[c - 'a'].isEnd) {
                    return true;
                }
                ch = ch[c - 'a'].children;
            }
        }
        return false;
    }
}
class WordDictionary {
    Trie dict;
    public WordDictionary() {
        dict = new Trie();
    }

    public void addWord(String word) {
        dict.insert(word);
    }
    public boolean search(String word) {
        return dict.searchChildren(word, 0, dict.children);
    }
}

public class Main {
    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        System.out.println(wordDictionary.search("a"));
        System.out.println(wordDictionary.search(".at"));
        wordDictionary.addWord("bat");
        System.out.println(wordDictionary.search(".at"));
        System.out.println(wordDictionary.search("an."));
        System.out.println(wordDictionary.search("a.d."));
        System.out.println(wordDictionary.search("b."));
        System.out.println(wordDictionary.search("a.d"));
        System.out.println(wordDictionary.search("."));
    }
}
