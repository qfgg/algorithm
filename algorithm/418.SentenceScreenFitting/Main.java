import java.util.*;


public class Main {
    public static int wordsTyping(String[] sentence, int rows, int cols) {
        int n = 0, r = 0, c = 0, sl = sentence.length;
        int[] wl = new int[sl];
        for (int i = 0; i < sl; i++) {
            wl[i] = sentence[i].length();
        }
        while(true) {
            for (int i = 0; i < sl; i++) {
                if (wl[i] > cols) {
                    return 0;
                }
                if (c + wl[i] - 1 < cols) {
                    c = c + wl[i] + 1;
                } else {
                    c = wl[i] + 1;
                    r++;
                    if (r == rows) {
                        return n;
                    }
                }
            }
            n++;
        }
    }
    public static void main(String[] args) {
        String[] sentence = new String[]{"a", "bcd", "e"};
        int res = wordsTyping(sentence, 3, 6);
        System.out.println(res);
    }
}
