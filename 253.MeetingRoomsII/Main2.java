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
        int num = 0;
        List<Integer> time = new ArrayList<>();
        int s, e;
        HashMap<Integer, Integer> start = new HashMap<>();
        HashMap<Integer, Integer> end = new HashMap<>();
        for (int[] it : itv) {
            s = start.containsKey(it[0]) ? start.get(it[0]) + 1 : 1;
            start.put(it[0], s);
            if (s == 1 && !end.containsKey(it[0])) {
                time.add(it[0]);
            }
            e = end.containsKey(it[1]) ? end.get(it[1]) + 1 : 1;
            end.put(it[1], e);
            if (e == 1 && !start.containsKey(it[1])) {
                time.add(it[1]);
            }
        }
        Collections.sort(time);
        for (int t : time) {
            if (start.containsKey((t))) {
                count += start.get(t);
            }
            if (end.containsKey(t)) {
                count -= end.get(t);
            }
            if (count > num) {
                num = count;
            }
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
