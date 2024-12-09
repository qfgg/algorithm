import java.util.*;

public class Main {
    public static int maxDistToClosest(int[] seats) {
        int max, l = 0, r = seats.length - 1, cnt = 0, tmp, i;
        while (seats[l] == 0) {
            l++;
        }
        while (seats[r] == 0) {
            r--;
            cnt++;
        }
        max = Math.max(l, cnt);
        i = l + 1;
        while (i <= r) {
            while (seats[i] == 1) {
                if (i == r) {
                    return max;
                }
                i++;
            }
            l = i - 1;
            while (seats[i] == 0) {
                i++;
                if (i > r) {
                    break;
                }
            }
            tmp = (i - l) / 2;
            if (tmp > max) {
                max = tmp;
            }
            l = i;
            i = l + 1;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] seats = new int[]{1,0,0,0,1,0,1};
        int ans = maxDistToClosest(seats);
        System.out.println(ans);
    }
}
