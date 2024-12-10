import java.util.*;


public class Main {
    public static boolean canTransform(String start, String end) {
        int l = start.length(), cntRX = 0, cntXL = 0;
        char s, e;
        for (int i = 0; i < l; i++) {
            s = start.charAt(i);
            e = end.charAt(i);
            if (s != e) {
                if (s == 'R' && e == 'X') {
                    if (cntXL > 0) {
                        return false;
                    }
                    cntRX++;
                } else if (s == 'X' && e == 'R') {
                    if (cntRX == 0) {
                        return false;
                    }
                    cntRX--;
                } else if (s == 'X' && e == 'L') {
                    if (cntRX > 0) {
                        return false;
                    }
                    cntXL++;
                } else if (s == 'L' && e == 'X') {
                    if (cntXL == 0) {
                        return false;
                    }
                    cntXL--;
                } else {
                    return false;
                }
            } else {
                if ((cntRX > 0 && s != 'X' && s != 'R') ||
                        (cntXL > 0 && e != 'X' && e!= 'L')) {
                    return false;
                }
            }
        }
        return cntRX == 0 && cntXL == 0;
    }
    public static void main(String args[]) {
        String start = "XLXRRXXRXX", end = "LXXXXXXRRR";
        boolean res = canTransform(start, end);
        System.out.println(res);
    }
}
