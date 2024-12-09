import java.util.*;

public class Main {
    public static int[] getOrder(int[][] tasks) {
        int n = tasks.length, i, k = 0, preEndTime = 0, endTime;
        int[][] t = new int[n][3];
        int[] cur;
        int[] res = new int[n];
        for (i = 0; i < n; i++) {
            t[i][0] = tasks[i][0];
            t[i][1] = tasks[i][1];
            t[i][2] = i;
        }
        Arrays.sort(t, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
        i = 0;
        while (i < n || !pq.isEmpty()) {
            if (!pq.isEmpty()) {
                cur = pq.poll();
                res[k] = cur[2];
                k++;
                endTime = preEndTime == 0 ? cur[0] + cur[1] : preEndTime + cur[1];
            } else {
                endTime = t[i][0];
            }
            while (i < n && t[i][0] <= endTime) {
                pq.add(t[i]);
                i++;
            }
            preEndTime = endTime;
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] tasks = new int[][]{
                {19,13},{16,9},{21,10},{32,25},{37,4},
                {49,24},{2,15},{38,41},{37,34},{33,6},
                {45,4},{18,18},{46,39},{12,24}
        };
        int[] res = getOrder(tasks);
        System.out.println(Arrays.toString(res));
    }
}
