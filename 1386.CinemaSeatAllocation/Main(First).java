import java.util.*;


public class Main {
//    availabilty state 0: 2-9, 1: 2-5, 2: 6-9, 3: 4-7, 4: 2-7, 5: 4-9
    public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, Integer> rowLeftRight = new HashMap<>();
        int ans = n * 2;
        int state;
        for (int[] rs : reservedSeats) {
            state = rowLeftRight.containsKey(rs[0]) ?
                    rowLeftRight.get(rs[0]) : 0;
            if (state == 0) {
                if (rs[1] == 2 || rs[1] == 3) {
                    rowLeftRight.put(rs[0], 5);
                    ans--;
                } else if (rs[1] == 4 || rs[1] == 5) {
                    rowLeftRight.put(rs[0], 2);
                    ans--;
                } else if (rs[1] == 6 || rs[1] == 7) {
                    rowLeftRight.put(rs[0], 1);
                    ans--;
                } else if (rs[1] == 8 || rs[1] == 9) {
                    rowLeftRight.put(rs[0], 4);
                    ans--;
                }
            } else if (state == 1) {
                if (rs[1] >= 2 && rs[1] <= 5) {
                    rowLeftRight.put(rs[0], -1);
                    ans--;
                }
            } else if (state == 2) {
                if (rs[1] >= 6 && rs[1] <= 9) {
                    rowLeftRight.put(rs[0], -1);
                    ans--;
                }
            } else if (state == 3) {
                if (rs[1] >= 4 && rs[1] <= 7) {
                    rowLeftRight.put(rs[0], -1);
                    ans--;
                }
            } else if (state == 4) {
                if (rs[1] == 2 || rs[1] == 3) {
                    rowLeftRight.put(rs[0], 3);
                } else if (rs[1] == 4 || rs[1] == 5) {
                    rowLeftRight.put(rs[0], -1);
                    ans--;
                } else if (rs[1] == 6 || rs[1] == 7) {
                    rowLeftRight.put(rs[0], 1);
                }
            } else if (state == 5) {
                if (rs[1] == 4 || rs[1] == 5) {
                    rowLeftRight.put(rs[0], 2);
                } else if (rs[1] == 6 || rs[1] == 7) {
                    rowLeftRight.put(rs[0], -1);
                    ans--;
                } else if (rs[1] == 8 || rs[1] == 9) {
                    rowLeftRight.put(rs[0], 3);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int n = 4;
        int[][] reservedSeats = new int[][]{{4,3},{1,4},{4,6},{1,7}};
        int ans = maxNumberOfFamilies(n, reservedSeats);
        System.out.println(ans);
    }
}
