import java.util.*;


public class Main {
    public static  int myAtoi(String s) {
        char[] chars = s.toCharArray();
        boolean isNeg = false;
        int i = 0 , j, k, n, len = s.length();
        while (i < len && chars[i] == ' ') {
            i++;
        }
        if (i < len && chars[i] == '+') {
            i++;
        } else if (i < len && chars[i] == '-') {
            i++;
            isNeg = true;
        }
        while (i < len && chars[i] == '0') {
            i++;
        }
        if (i < len && chars[i] < '0' && chars[i] > '9') {
            return 0;
        }
        char[] max = new char[]{'2','1','4','7','4','8','3','6','4'};
        j = i;
        while(j < len && chars[j] >= '0' && chars[j] <='9') {
            j++;
        }
        int maxLen = max.length;
        if (j - i > maxLen + 1) {
            return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        } else if (j - i == maxLen + 1) {
            k = i;
            n = 0;
            while (n < maxLen) {
                if (chars[k] > max[n]) {
                    return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                if (chars[k] < max[n]) {
                    break;
                }
                k++;
                n++;
            }
            if (n == maxLen) {
                if (isNeg && chars[k] >= '8') {
                    return Integer.MIN_VALUE;
                }
                if (!isNeg && chars[k] >= '7') {
                    return Integer.MAX_VALUE;
                }
            }
        }
        int ret = 0;
        while (i < j) {
            ret = ret * 10 + (chars[i] - '0');
            i++;
        }
        return isNeg ? -1 * ret : ret;
    }

    public static void main(String[] args) {
        String s = "+-42";
        int ans = myAtoi(s);
        System.out.println(ans);
    }
}
