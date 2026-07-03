import java.util.*;


class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] remainerCnt = new int[60];
        int total = 0, i, len = time.length, cur;
        for (i =0; i < len; i++) {
            cur = time[i] % 60;
            total += remainerCnt[(60 - cur) % 60];
            remainerCnt[cur]++;
        }
        return total;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] time = new int[]{30,30,20,150,100,40};
        int res = s.numPairsDivisibleBy60(time);
        System.out.println(res);
    }
}
