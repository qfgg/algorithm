import java.util.*;


class Solution {
    public String originalDigits(String s) {
        int[] digits = new int[10];
        int[] count = new int[26];
        char[] c = s.toCharArray();
        int n = c.length, i, j, num;
        for (i = 0; i < n ; i++) {
            count[c[i] - 'a']++;
        }
        if (count['z' - 'a'] > 0) {
            num = count['z' - 'a'];
            count['z' - 'a'] -= num;
            count['e' - 'a'] -= num;
            count['r' - 'a'] -= num;
            count['o' - 'a'] -= num;
            digits[0] = num;
        }
        if (count['w' - 'a'] > 0) {
            num = count['w' - 'a'];
            count['t' - 'a'] -= num;
            count['w' - 'a'] -= num;
            count['o' - 'a'] -= num;
            digits[2] = num;
        }
        if (count['x' - 'a'] > 0) {
            num = count['x' - 'a'];
            count['s' - 'a'] -= num;
            count['i' - 'a'] -= num;
            count['x' - 'a'] -= num;
            digits[6] = num;
        }
        if (count['u' - 'a'] > 0) {
            num = count['u' - 'a'];
            count['f' - 'a'] -= num;
            count['o' - 'a'] -= num;
            count['u' - 'a'] -= num;
            count['r' - 'a'] -= num;
            digits[4] = num;
        }
        if (count['f' - 'a'] > 0) {
            num = count['f' - 'a'];
            count['f' - 'a'] -= num;
            count['i' - 'a'] -= num;
            count['v' - 'a'] -= num;
            count['e' - 'a'] -= num;
            digits[5] = num;
        }
        if (count['v' - 'a'] > 0) {
            num = count['v' - 'a'];
            count['s' - 'a'] -= num;
            count['e' - 'a'] -= 2 * num;
            count['v' - 'a'] -= num;
            count['n' - 'a'] -= num;
            digits[7] = num;
        }
        if (count['g' - 'a'] > 0) {
            num = count['g' - 'a'];
            count['e' - 'a'] -= num;
            count['i' - 'a'] -= num;
            count['g' - 'a'] -= num;
            count['h' - 'a'] -= num;
            count['t' - 'a'] -= num;
            digits[8] = num;
        }
        if (count['h' - 'a'] > 0) {
            num = count['h' - 'a'];
            count['t' - 'a'] -= num;
            count['h' - 'a'] -= num;
            count['r' - 'a'] -= num;
            count['e' - 'a'] -= 2 * num;
            digits[3] = num;
        }
        if (count['o' - 'a'] > 0) {
            num = count['o' - 'a'];
            count['o' - 'a'] -= num;
            count['n' - 'a'] -= num;
            count['e' - 'a'] -= num;
            digits[1] = num;
        }
        if (count['i' - 'a'] > 0) {
            num = count['i' - 'a'];
            count['n' - 'a'] -= 2 * num;
            count['i' - 'a'] -= num;
            count['e' - 'a'] -= num;
            digits[9] = num;
        }
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < 10; i++) {
            if (digits[i] > 0) {
                sb.append(String.valueOf((char)(i + '0')).repeat(digits[i]));
            }
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "ereht";
        String res = s.originalDigits(str);
        System.out.println(res);
    }
}
