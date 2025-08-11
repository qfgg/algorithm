import java.util.*;


class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length, i, preCnt = 1, cnt = 0;
        for (i = n - 2; i >= n - k; i--) {
            if (1 - colors[i] == colors[i + 1]) {
                preCnt++;
            } else {
                break;
            }
        }
        if (preCnt == k) {
            cnt++;
        }
        if (1 - colors[0] == colors[n - 1]) {
            preCnt++;
        } else {
            preCnt = 1;
        }
        if (preCnt >= k) {
            cnt++;
        }
        for (i = 1; i < n - 1; i++) {
            if (1 - colors[i] == colors[i - 1]) {
                preCnt++;
            } else {
                preCnt = 1;
            }
            if (preCnt >= k) {
                cnt++;
            }
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] colors = new int[]{0,1,0,0,1,0,1};
        int k = 6;
        long res = s.numberOfAlternatingGroups(colors, k);
        System.out.println(res);
    }
}
