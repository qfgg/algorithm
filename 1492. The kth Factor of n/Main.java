import java.util.*;


class Solution {
    public int kthFactor(int n, int k) {
        if (k == 1) {
            return 1;
        }
        int end = (int)Math.sqrt(n), i, count = 1;
        List<Integer> head = new ArrayList<>(List.of(1));
        for (i = 2; i <= end; i++) {
            if (n % i == 0) {
                head.add(i);
                count++;
                if (count == k) {
                    return i;
                }
            }
        }
        int hn = head.size(), total = end * end == n ? hn * 2 - 1 : hn * 2;
        if (k > total) {
            return -1;
        }
        return n / head.get(total - k);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 12, k = 4;
        int res = s.kthFactor(n, k);
        System.out.println(res);
    }
}
