import java.util.*;

// 0b0000000000 represents vacancy of a row
public class Main {
    public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, Integer> seatState = new HashMap<>();
        int ans = n * 2, curState;
        for (int[] rs : reservedSeats) {
            if (rs[1] == 1 || rs[1] == 10) {
                continue;
            }
            curState = seatState.getOrDefault(rs[0], 0);
            if (curState == 0b0000000000) {
                if (rs[1] < 4) {
                    seatState.put(rs[0], 0b0110000000);
                } else if (rs[1] < 6) {
                    seatState.put(rs[0], 0b0111100000);
                } else if (rs[1] < 8) {
                    seatState.put(rs[0], 0b0000011110);
                } else {
                    seatState.put(rs[0], 0b0000000110);
                }
                ans--;
            } else if (curState == 0b0000000110) {
                if (rs[1] < 4) {
                    seatState.put(rs[0], 0b0110000110);
                } else if (rs[1] < 6) {
                    seatState.put(rs[0], 0b0111111110);
                    ans--;
                } else if (rs[1] < 8) {
                    seatState.put(rs[0], 0b0000011110);
                }
            } else if (curState == 0b0110000000) {
                if (rs[1] > 7) {
                    seatState.put(rs[0], 0b0110000110);
                } else if (rs[1] > 5) {
                    seatState.put(rs[0], 0b0111111110);
                    ans--;
                } else if (rs[1] > 3) {
                    seatState.put(rs[0], 0b0111100000);
                }
            } else if (curState == 0b0000011110) {
                if (rs[1] < 6) {
                    seatState.put(rs[0], 0b0111111110);
                    ans--;
                }
            } else if (curState == 0b0110000110) {
                if (rs[1] > 3 && rs[1] < 8) {
                    seatState.put(rs[0], 0b0111111110);
                    ans--;
                }
            } else if (curState == 0b0111100000) {
                if (rs[1] > 5) {
                    seatState.put(rs[0], 0b0111111110);
                    ans--;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int n = 3;
        int[][] reservedSeats = new int[][]{{1,2},{1,3},{1,8},{2,6},{3,1},{3,10}};
        int ans = maxNumberOfFamilies(n, reservedSeats);
        System.out.println(ans);
    }
}
