import java.util.*;


class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 7;
        int res = s.bulbSwitch(n);
        System.out.println(res);
    }
}
