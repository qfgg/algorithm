import java.util.*;

public class Main {
    public static int findMinDifference(List<String> timePoints) {
        int[] time = new int[1440];
        for (String t : timePoints) {
            int m = ((t.charAt(0) - '0') * 10 + (t.charAt(1) - '0')) * 60
                    + (t.charAt(3) - '0') * 10 + (t.charAt(4) - '0');
            if (time[m] > 0) {
                return 0;
            }
            time[m]++;
        }
        int diff = Integer.MAX_VALUE, first = 0, pre = -1;
        for (int i = 0; i < 1440; i++) {
            if (time[i] > 0) {
                if (pre >= 0) {
                    diff = Math.min(diff, i - pre);
                } else {
                    first = i;
                }
                pre = i;
            }
        }
        return Math.min(first + 1440 - pre, diff);
    }
    public static void main(String[] args) {
        List<String> timePoints = new ArrayList<>(Arrays.asList(new String[]{"05:31","22:08","00:35"}));
        int ans = findMinDifference(timePoints);
        System.out.println(ans);
    }
}
