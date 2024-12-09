import java.util.*;

class Trie {
    boolean isEnd;
    Trie[] next;
    public Trie() {
        next = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        int n = word.length();
        char c;
        Trie[] nt = next;
        for (int i = 0; i < n; i++) {
            c = word.charAt(i);
            if (nt[c - 'a'] == null) {
                nt[c - 'a'] = new Trie();
            }
            if (i == n - 1) {
                nt[c - 'a'].isEnd = true;
                break;
            }
            nt = nt[c - 'a'].next;
        }
    }

    public boolean search(String word) {
        int n = word.length();
        char c;
        Trie[] nt = next;
        for (int i = 0; i < n; i++) {
            c = word.charAt(i);
            if (nt[c - 'a'] == null) {
                return false;
            }
            if (i == n - 1 && nt[c - 'a'].isEnd) {
                return true;
            }
            nt = nt[c - 'a'].next;
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        int n = prefix.length();
        char c;
        Trie[] nt = next;
        for (int i = 0; i < n; i++) {
            c = prefix.charAt(i);
            if (nt[c - 'a'] == null) {
                return false;
            }
            nt = nt[c - 'a'].next;
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // return True
        System.out.println(trie.search("app"));     // return False
        System.out.println(trie.startsWith("app")); // return True
        trie.insert("app");
        System.out.println(trie.search("app"));     // return True
    }
}
