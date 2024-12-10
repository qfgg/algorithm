import java.util.*;


public class Main {
    public static boolean canChange(String start, String target) {
        int i = 0, j = 0, n1 = start.length(), n2 = target.length(), cnt1 = 0, cnt2 = 0;
        while (i <= n1 && j <= n2) {
            while (i < n1 && start.charAt(i) != 'L' && start.charAt(i) != 'R') {
                cnt1++;
                i++;
            }
            while (j < n2 && target.charAt(j) != 'L' && target.charAt(j) != 'R') {
                cnt2++;
                j++;
            }
            if (i == n1) {
                return j == n2;
            }
            if (j == n2 ||
                    start.charAt(i) != target.charAt(j) ||
                    (start.charAt(i) == 'L' && target.charAt(j) == 'L' && cnt1 < cnt2) ||
                    (start.charAt(i) == 'R' && target.charAt(j) == 'R' && cnt1 > cnt2)) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }
    public static void main(String[] args) {
        String start = "_L__R__R_", target = "L______RR";
        boolean res = canChange(start, target);
        System.out.println(res);
    }
}
