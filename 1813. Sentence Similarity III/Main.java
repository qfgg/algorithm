import java.util.*;


class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        if (sentence1.equals(sentence2)) {
            return true;
        }
        String[] w1 = sentence1.split(" ");
        String[] w2 = sentence2.split(" ");
        String[] s, l;
        if (w1.length > w2.length) {
            s = w2;
            l = w1;
        } else {
            s = w1;
            l = w2;
        }
        int sh = 0 , lh = 0, sn = s.length, ln = l.length;
        while (sh < sn && lh < ln && s[sh].equals(l[lh])) {
            sh++;
            lh++;
        }
        if (sh == sn) {
            return true;
        }
        int st = sn - 1, lt = ln - 1;
        while (st >= sh && lt >= lh && s[st].equals(l[lt])) {
            st--;
            lt--;
        }
        if (st < sh) {
            return true;
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String sentence1 = "My name is Haley", sentence2 = "My Haley";
        System.out.println(s.areSentencesSimilar(sentence1, sentence2));
    }
}
