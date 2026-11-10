import java.util.*;


class Solution {
    String map(String word, double p) {
        if (word.length() < 2 || !word.startsWith("$")) {
            return word;
        }
        int i, n = word.length();
        char cur;
        for (i = 1; i < n; i++) {
            cur = word.charAt(i);
            if (cur < '0' || cur > '9') {
                return word;
            }
        }
        return "$" + String.format("%.2f", Double.parseDouble(word.substring(1)) * p);
    }
    public String discountPrices(String sentence, int discount) {
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        double p = (double)(100 - discount) / 100;
        sb.append(map(words[0], p));
        int i, n = words.length;
        for (i = 1; i < n; i++) {
            sb.append(" ");
            sb.append(map(words[i], p));
        }
        return sb.toString();
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      String sentence = "$76111 ab $6 $";
      int discount = 50;
      String res = s.discountPrices(sentence, discount);
      System.out.println(res);
  }
}
