import java.util.*;

class Sortbystart implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
        return a[0] - b[0];
    }
}

class Main {
    public static void merge2(int[] next, List<int[]> result) {
        int[] pre = result.get(result.size() - 1);
        if (pre[1] < next[0]) {
            result.add(next);
        } else {
            pre[1] = Math.max(pre[1], next[1]);
        }
    }
    public static int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if (len == 1) {
            return intervals;
        }
        List<int[]> merged = new ArrayList<>();
        Arrays.sort(intervals, new Sortbystart());
        merged.add(intervals[0]);
        for (int i = 1; i < len; i++){
            merge2(intervals[i], merged);
        }
        len = merged.size();
        int[][] ret = new int[len][2];
        for (int i = 0; i < len; i++) {
            int[] it = merged.get(i);
            ret[i] = it;
        }
        return ret;
    }
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] ret = merge(intervals);
        System.out.println(Arrays.deepToString(ret));
    }
}
