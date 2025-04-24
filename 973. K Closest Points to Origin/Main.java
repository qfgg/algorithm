import java.util.*;


class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxheap = new PriorityQueue<>((a, b) -> b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1]);
        int[][] res = new int[k][];
        int i, len = points.length;
        for (i = 0; i < k; i++) {
            maxheap.add(points[i]);
        }
        while (i < len) {
            maxheap.add(points[i]);
            maxheap.poll();
            i++;
        }
        i = 0;
        while (!maxheap.isEmpty()) {
            res[i] = maxheap.poll();
            i++;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] points = new int[][]{
                {3,3},
                {5,-1},
                {-2,4},
        };
        int k = 2;
        int[][] res = s.kClosest(points, k);
        System.out.println(Arrays.deepToString(res));
    }
}
