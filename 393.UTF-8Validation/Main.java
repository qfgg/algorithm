import java.util.*;

class Solution {
    public int check(int[] data, int i, int n) {
        int num = 0, j;
        if (i < n) {
            if ((data[i] >> 7) == 0) {
                return i + 1;
            }
            if ((data[i] >> 5) == 6) {
                num = 1;
            } else if ((data[i] >> 4) == 14) {
                num = 2;
            } else if ((data[i] >> 3) == 30) {
                num = 3;
            } else {
                return -1;
            }
            for (j = 1; j <= num; j++) {
                if (i + j >= n || (data[i + j] >> 6) != 2) {
                    return -1;
                }
            }
        }
        return i + num + 1;
    }
    public boolean validUtf8(int[] data) {
        int i = 0, n = data.length;
        while (i < n) {
            i = check(data, i, n);
            if (i == -1) {
                return false;
            }
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] data = new int[]{237};
        boolean res = s.validUtf8(data);
        System.out.println(res);
    }
}
