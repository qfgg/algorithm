class Trie {
    private Trie[] ch;
    private boolean isEnd;
    private List<String> words;
    Trie() {
        ch = new Trie[26];
        isEnd = false;
        words = null;
    }
    public void insert(String word) {
        Trie cur = this;
        int i, idx, len = word.length();
        for (i = 0; i < len; i++) {
            idx = word.charAt(i) - 'a';
            if (cur.ch[idx] == null) {
                cur.ch[idx] = new Trie();
            }
            if (cur.ch[idx].words == null) {
                cur.ch[idx].words = new ArrayList<>();
            }
            if (cur.ch[idx].words.size() < 3) {
              cur.ch[idx].words.add(word);
            }
            cur = cur.ch[idx];
        }
        cur.isEnd = true;
    }
    public List<List<String>> startWith(String word) {
        List<List<String>> ret = new ArrayList<>();
        Trie cur = this;
        int i, idx, len = word.length(), j = len;
        for (i = 0; i < len; i++) {
            idx = word.charAt(i) - 'a';
            if (cur.ch[idx] == null) {
                j = i;
                break;
            }
            ret.add(cur.ch[idx].words);
            cur = cur.ch[idx];
        }
        while (j < len) {
            ret.add(new ArrayList<>());
            j++;
        }
        return ret;
    }
}
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie t = new Trie();
        Arrays.sort(products);
        for (String pd : products) {
            t.insert(pd);
        }
        List<List<String>> ret = t.startWith(searchWord);
        return ret;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
      String searchWord = "mouse";
      List<List<String>> res = s.suggestedProducts(products, searchWord);
      System.out.println(res);
  }
}

