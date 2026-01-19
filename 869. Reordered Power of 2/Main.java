import java.util.*;


class Solution {
    String getBits(int num, StringBuilder sb) {
        int[] ch = new int[10];
        while (num > 0) {
            ch[num % 10]++;
            num = num / 10;
        }
        for (int i = 9; i >= 0; i--) {
            while (ch[i] > 0) {
                sb.append(i);
                ch[i]--;
            }
        }
        String ret = sb.toString();
        sb.setLength(0);
        return ret;
    }
    public boolean reorderedPowerOf2(int n) {
        if (n == 1) {
            return true;
        }
        int i, num = 1;
        StringBuilder sb = new StringBuilder();
        String reorder = getBits(n, sb);
        for (i = 1; i < 31; i++) {
            num = num << 1;
            if (reorder.equals(getBits(num, sb))) {
                return true;
            }
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        boolean res = s.reorderedPowerOf2(526);
        System.out.println(res);
    }
}
