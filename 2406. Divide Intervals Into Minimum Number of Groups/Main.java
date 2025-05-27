import java.util.*;


class Solution {
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<List<Integer>> groups = new ArrayList<>();
        List<Integer> group = new ArrayList<>(List.of(0));
        groups.add(group);
        PriorityQueue<int[]> earliest = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        earliest.add(new int[]{0, intervals[0][1]});
        int n = intervals.length, i;
        int[] lastEnd;
        for (i = 1; i < n; i++) {
            lastEnd = earliest.poll();
            if (intervals[i][0] > lastEnd[1]) {
                groups.get(lastEnd[0]).add(i);
                lastEnd[1] = intervals[i][1];
            } else {
                groups.add(new ArrayList<>(List.of(i)));
                earliest.add(new int[]{groups.size() - 1, intervals[i][1]});
            }
            earliest.add(lastEnd);
        }
        return groups.size();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] intervals = new int[][]{
                {5, 10},
                {6, 8},
                {1, 5},
                {2, 3},
                {1, 10},
        };
        int res = s.minGroups(intervals);
        System.out.println(res);
    }
}
