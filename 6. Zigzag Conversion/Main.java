import java.util.*;


class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] sb = new StringBuilder[numRows];
        char[] c = s.toCharArray();
        int i, j, len = c.length;
        for (i = 0; i < numRows; i++) {
            sb[i] = new StringBuilder();
        }
        i = 0;
        while (i < len) {
            j = 0;
            while (j < numRows && i + j < len) {
                sb[j].append(c[i + j]);
                j++;
            }
            if (i + j == len) {
                break;
            }
            i = i + numRows;
            j = 0;
            while (j < numRows - 2 && i + j < len) {
                sb[numRows - 2 - j].append(c[i + j]);
                j++;
            }
            if (i + j == len) {
                break;
            }
            i = i + numRows - 2;
        }
        for (i = 1; i < numRows; i++) {
            sb[0].append(sb[i]);
        }
        return sb[0].toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "A";
        int numRows = 1;
        String res = s.convert(str, numRows);
        System.out.println(res);
    }
}
