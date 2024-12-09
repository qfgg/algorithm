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
        int count = 0;
        Arrays.sort(itv, Comparator.comparing((int[] it) -> it[0]));
        PriorityQueue<Integer> firstDone = new PriorityQueue<>();
        for (int[] it : itv) {
            if (firstDone.isEmpty()) {
                firstDone.add(it[1]);
                count++;
            } else {
                int firstEnd = firstDone.peek();
                if (firstEnd > it[0]) {
                    count++;
                } else {
                    firstDone.poll();
                }
                firstDone.add(it[1]);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] itv = genItv();
        int ans = meetingRoom2(itv);
        System.out.println(Arrays.deepToString(itv));
        System.out.println(ans);
    }
}
