import java.util.*;

class Solution {
    public List<Integer> lexicalOrder(int n) {
        int breakpoint;
        if (n >= 9000) {
            breakpoint = 9999;
        } else if (n >= 900) {
            breakpoint = 999;
        } else if (n >= 90) {
            breakpoint = 99;
        } else {
            breakpoint = 9;
        }
        List<Integer> res = new ArrayList<>();
        int i = 1;
        while (true) {
            res.add(i);
            while (i * 10 <= n) {
                i = i * 10;
                if (i <= n) {
                    res.add(i);
                }
            }
            while (i % 10 != 9) {
                i++;
                if (i <= n) {
                    res.add(i);
                }
            }
            if (i == breakpoint) {
                break;
            }
            i++;
            while (i % 10 == 0) {
                i = i / 10;
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Integer> res = s.lexicalOrder(189);
        for (Integer r : res) {
            System.out.println(r);
        }
    }
}
