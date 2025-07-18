import java.util.*;


class Solution {
    public int findTheWinner(int n, int k) {
        HashSet<Integer> gone = new HashSet<>();
        int i = 0, cnt = 0;
        while (gone.size() < n - 1) {
            i = (i + 1) % n;
            while (gone.contains(i)) {
                i = (i + 1) % n;
            }
            cnt++;
            if (cnt == k) {
                gone.add(i);
                cnt = 0;
            }
        }
        for (i = 1; i <= n; i++) {
            if (!gone.contains(i)) {
                break;
            }
        }
        return i;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 6, k = 5;
        int res = s.findTheWinner(n, k);
        System.out.println(res);
    }
}
