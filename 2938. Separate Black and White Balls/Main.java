import java.util.*;


class Solution {
    public long minimumSteps(String s) {
        int r = s.length() - 1;
        long count = 0, curSum = 0, curZeros = 0;
        while (r >= 0) {
            if (s.charAt(r) == '0') {
                curZeros++;
            } else {
                curSum += curZeros;
                count += curSum;
                curZeros = 0;
            }
            r--;
        }
        return count;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "10101001";
        long res = s.minimumSteps(str);
        System.out.println(res);
    }
}
