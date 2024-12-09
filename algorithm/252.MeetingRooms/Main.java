import java.util.*;


class Main {
    public static int[][] genItv() {
        Random rand = new Random();
        int num = rand.nextInt(5) + 1;
        int[][] itv = new int[num][2];
        int offset;
        for (int[] i : itv) {
            i[0] = rand.nextInt(13);
            if (rand.nextInt(3) == 1) {
                offset = rand.nextInt(12) + 1;
            } else {
                offset = rand.nextInt(3) + 1;
            }
            i[1] = i[0] + offset;
        }
        return itv;
    }
    public static boolean meetingRoom(int[][] itv) {
        Arrays.sort(itv, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < itv.length - 2; i++) {
            if (itv[i][1] > itv[i + 1][0]) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        int[][] itv = genItv();
        boolean ans = meetingRoom(itv);
        System.out.println(Arrays.deepToString(itv));
        System.out.println(ans);
    }
}
