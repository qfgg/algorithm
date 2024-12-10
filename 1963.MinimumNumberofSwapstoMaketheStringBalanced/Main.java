import java.util.*;


public class Main {
    public static int minSwaps(String s) {
        char[] chars = s.toCharArray();
        int min = 0, cnt = 0;
        for (char c : chars) {
            if (c == '[') {
                cnt++;
            } else if (c == ']') {
                cnt--;
                if (cnt < min) {
                    min = cnt;
                }
            }
        }
        if (min == 0) {
            return 0;
        }
        min = -1 * min;
        return min % 2 == 0 ? min / 2 : min / 2 + 1;
    }
    public static void main(String[] args) {
        String s = "]]][[[";
        int ans = minSwaps(s);
        System.out.println(ans);
    }
}
