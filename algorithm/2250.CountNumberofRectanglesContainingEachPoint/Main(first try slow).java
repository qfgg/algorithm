import java.util.*;

public class Main {
    public static void groupRect(List<int[]> l, List<List<int[]>> groups) {
        int n, pre, i;
        List<int[]> rest = new ArrayList<>();
        List<int[]> tmp;
        while (true) {
            List<int[]> group = new ArrayList<>();
            n = l.size();
            pre = 0;
            group.add(l.get(0));
            groups.add(group);
            for (i = 1; i < n; i++) {
                if ((l.get(i)[0] >= l.get(pre)[0] &&
                        l.get(i)[1] >= l.get(pre)[1]) ||
                        (l.get(i)[0] <= l.get(pre)[0] &&
                                l.get(i)[1] <= l.get(pre)[1])) {
                    group.add(l.get(i));
                    pre = i;
                } else {
                    rest.add(l.get(i));
                }
            }
            if (rest.isEmpty()) {
                break;
            }
            tmp = l;
            l = rest;
            rest = tmp;
            rest.clear();
        }
    }

    public static int binarySearch(List<int[]> group, int[] point, int idx) {
        int l = 0, n = group.size(), r = n, m;
        int[] cur;
        while (l < r) {
            m = l + ((r - l) >> 1);
            cur = group.get(m);
            if (point[idx] > cur[idx]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        if (l == n) {
            return 0;
        }
        return n - l;
    }
    public static int countRectInAGroup(List<int[]> group, int[] point) {
        return Math.min(
                binarySearch(group, point, 0),
                binarySearch(group, point, 1)
        );
    }
    public static int[] countRectangles(int[][] rectangles, int[][] points) {
        Arrays.sort(rectangles, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<int[]> l = new ArrayList<>();
        Collections.addAll(l, rectangles);
        List<List<int[]>> groups = new ArrayList<>();
        groupRect(l, groups);
        int m = points.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            for (List<int[]> group : groups) {
                res[i] += countRectInAGroup(group, points[i]);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] rectangles = new int[][]{
                {4,4},{1,2},{2,3},{2,5},{3,1}
        }, points = new int[][]{
                {2,1},{1,4}
        };
        int[] res = countRectangles(rectangles, points);
        System.out.println(Arrays.toString(res));
    }
}
