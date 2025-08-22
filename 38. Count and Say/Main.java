import java.util.*;


class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        StringBuilder sb = new StringBuilder();
        List<Character> cur = new ArrayList<>(List.of('1')), next = new ArrayList<>(), tmp;
        int len, i, j, cnt;
        char curCh;
        for (i = 2; i <= n; i++) {
            len = cur.size();
            cnt = 1;
            curCh = cur.getFirst();
            for (j = 0; j < len; j++) {
                if (j == len - 1 || cur.get(j + 1) != curCh) {
                    next.add((char) ('0' + cnt));
                    next.add(curCh);
                    if (j < len - 1) {
                        cnt = 1;
                        curCh = cur.get(j + 1);
                    }
                } else {
                    cnt++;
                }
            }
            tmp = cur;
            cur = next;
            next = tmp;
            next.clear();
        }
        for(char c : cur) {
            sb.append(c);
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 4;
        String res = s.countAndSay(n);
        System.out.println(res);
    }
}
