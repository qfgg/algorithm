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
    public static int meetingRoom2(int[][] itv) {
        if (itv.length == 0) {
            return 0;
        }
        Arrays.sort(itv, (a, b) -> a[0] - b[0]);
        int num = 1, i, j, n, pre = 0;
        List<List<int[]>> mr = new ArrayList<>();
        for (i = 1; i < itv.length; i++) {
            if (itv[pre][1] > itv[i][0]) {
                n = mr.size();
                for (j = 0; j < n; j++) {
                    List<int[]> l = mr.get(j);
                    int[] tail = l.get(l.size() - 1);
                    if (tail[1] <= itv[i][0]) {
                        l.add(itv[i]);
                        break;
                    }
                }
                if (n == j) {
                    num++;
                    mr.add(new ArrayList<>(Arrays.asList(itv[i])));
                }
            } else {
                pre = i;
            }
        }
        for (i = 0; i < mr.size(); i++) {
            System.out.println(Arrays.deepToString(mr.get(i).toArray()));
        }
        return num;
    }

    public static void main(String[] args) {
        int[][] itv = genItv();
        int ans = meetingRoom2(itv);
        System.out.println(Arrays.deepToString(itv));
        System.out.println(ans);
    }
}
