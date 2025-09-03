import java.util.*;


class Solution {
    void count(String str, List<Character> ch, List<Integer> cnt) {
        int n = str.length(), i, curCnt = 1;
        char pre = str.charAt(0), cur;
        for (i = 1; i < n; i++) {
            cur = str.charAt(i);
            if (cur != pre) {
                ch.add(pre);
                cnt.add(curCnt);
                curCnt = 1;
                pre = cur;
            } else {
                curCnt++;
            }
        }
        ch.add(pre);
        cnt.add(curCnt);
    }
    boolean isStretchy(String str, List<Character> ch, List<Integer> cnt) {
        int n = str.length(), i, curCnt = 1, j = 0, targetCnt;
        char pre = str.charAt(0), cur;
        for (i = 1; i < n; i++) {
            cur = str.charAt(i);
            if (cur != pre) {
                if (ch.get(j) != pre) {
                    return false;
                }
                targetCnt = cnt.get(j);
                if (targetCnt < curCnt || (targetCnt > curCnt && targetCnt < 3)) {
                    return false;
                }
                curCnt = 1;
                pre = cur;
                j++;
                if (j == ch.size()) {
                    return false;
                }
            } else {
                curCnt++;
            }
        }
        if (ch.get(j) != pre) {
            return false;
        }
        targetCnt = cnt.get(j);
        return j == ch.size() - 1 && ((targetCnt > curCnt && targetCnt >= 3) || curCnt == targetCnt);
    }
    public int expressiveWords(String s, String[] words) {
        List<Integer> cnt =  new ArrayList<>();
        List<Character> ch = new ArrayList<>();
        count(s, ch, cnt);
        int total = 0;
        for (String str : words) {
            if (isStretchy(str, ch, cnt)) {
                total++;
            }
        }
        return total;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "abcd";
        String[] words = new String[]{"abc"};
        int res = s.expressiveWords(str, words);
        System.out.println(res);
    }
}
