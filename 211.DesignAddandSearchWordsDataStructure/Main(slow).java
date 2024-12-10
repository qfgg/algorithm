import java.util.*;

class WordDictionary {
    Set<String> dict;
    public WordDictionary() {
        dict = new HashSet<>();
    }

    public void addWord(String word) {
        dict.add(word);
    }

    public boolean compare(String w, String target) {
        int n = w.length(), i;
        for (i = 0; i < n; i++) {
            if (target.charAt(i) != '.' && target.charAt(i) != w.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public boolean search(String word) {
        if (dict.contains(word)) {
            return true;
        }
        if (word.indexOf('.') == -1) {
            return false;
        }
        for (String w : dict) {
            if (w.length() == word.length()) {
                if (compare(w, word)) {
                    return true;
                }
            }
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }
}
