import java.util.*;


class Solution {
    public String removeDuplicates(String s, int k) {
        char[] ch = s.toCharArray();
        int i, n = ch.length, cnt = 0;
        char cur = 0;
        Deque<Character> lStack = new ArrayDeque<>();
        Deque<Integer> cStack = new ArrayDeque<>();
        for (i = 0; i < n; i++) {
            if (cur == 0) {
                cur = ch[i];
                cnt = 1;
            } else if (ch[i] == cur) {
                cnt++;
                if (cnt == k) {
                    cur = lStack.isEmpty() ? 0 : lStack.pop();
                    cnt = cStack.isEmpty() ? 0 : cStack.pop();
                }
            } else {
                lStack.push(cur);
                cStack.push(cnt);
                cur = ch[i];
                cnt = 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!lStack.isEmpty()) {
            sb.repeat(lStack.pop(), cStack.pop());
        }
        sb.reverse();
        String ret = "";
        if (cur != 0) {
            ret = sb.repeat(cur, cnt).toString();
        }
        return ret;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      String str = "pbbcggttciiippooaais";
      int k = 2;
      String ret = s.removeDuplicates(str, k);
      System.out.println(ret);
  }
}
