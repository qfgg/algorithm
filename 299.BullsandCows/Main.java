import java.util.*;

class Solution {
    public String getHint(String secret, String guess) {
        int n = secret.length(), i, bn = 0, cn = 0;
        boolean[] bulls = new boolean[n];
        int[] cnt = new int[10];
        char c1, c2;
        for (i = 0; i < n; i++) {
            c1 = secret.charAt(i);
            c2 = guess.charAt(i);
            if (c1 == c2) {
                bulls[i] = true;
                bn++;
            } else {
                cnt[c1 - '0']++;
            }
        }
        for (i = 0; i < n; i++) {
            if (!bulls[i]) {
                c1 = guess.charAt(i);
                if (cnt[c1 - '0'] > 0) {
                    cnt[c1 - '0']--;
                    cn++;
                }
            }
        }
        return Integer.toString(bn) + 'A' + cn + 'B';
    }
}
public class Main {
    public static void main(String[] args) {
        Solution st = new Solution();
        String secret = "1807", guess = "7810";
        String res = st.getHint(secret, guess);
        System.out.println(res);
    }
}
