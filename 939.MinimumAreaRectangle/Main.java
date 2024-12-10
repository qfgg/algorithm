import java.util.*;

public class Main {
    public static void deletePoints(int[] point, Map<Integer, Integer> xCnt, Map<Integer, Integer> yCnt, List<int[]> nt) {
        int c;
        if (xCnt.get(point[0]) == 1) {
            xCnt.remove(point[0]);
            c = yCnt.get(point[1]);
            if (c > 1) {
                yCnt.put(point[1], c - 1);
            } else {
                yCnt.remove(point[1]);
            }
        } else if (yCnt.get(point[1]) == 1) {
            yCnt.remove(point[1]);
            c = xCnt.get(point[0]);
            if (c > 1) {
                xCnt.put(point[0], c - 1);
            } else {
                xCnt.remove(point[0]);
            }
        } else {
            nt.add(point);
        }
    }
    public static void delete(List<int[]> pt, Map<Integer, Integer> xCnt, Map<Integer, Integer> yCnt) {
        List<int[]> nt = new ArrayList<>();
        List<int[]> tmp;
        int[] p;
        while (true) {
            for (int i = pt.size() - 1; i >= 0; i--) {
                p = pt.get(i);
                deletePoints(p, xCnt, yCnt, nt);
            }
            if (nt.size() == pt.size()) {
                break;
            }
            tmp = pt;
            pt = nt;
            nt = tmp;
            nt.clear();
        }
    }
    public static int findMin(List<int[]> pt) {
        int i, j, m, n, min = Integer.MAX_VALUE, cn, ln, rn, area;
        Collections.sort(pt, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<List<int[]>> cols = new ArrayList<>();
        List<int[]> l, r;
        int prex = -1, preh;
        for (int[] p : pt) {
            if (p[0] != prex) {
                cols.add(new ArrayList<>());
                prex = p[0];
            }
            cols.get(cols.size() - 1).add(p);
        }
        cn = cols.size();
        for (i = 1; i < cn; i++) {
            for (j = 0; j < cn - i; j++) {
                l = cols.get(j);
                r = cols.get(j + i);
                ln = l.size();
                rn = r.size();
                m = 0;
                n = 0;
                preh = -1;
                while (m < ln && n < rn) {
                    while (m < ln && n < rn && l.get(m)[1] < r.get(n)[1]) {
                        m++;
                    }
                    while (m < ln && n < rn && r.get(n)[1] < l.get(m)[1]) {
                        n++;
                    }
                    if (m < ln && n < rn && r.get(n)[1] == l.get(m)[1]) {
                        if (preh != - 1) {
                            area = (r.get(n)[1] - preh) * (r.get(n)[0] - l.get(m)[0]);
                            if (area < min) {
                                min = area;
                            }
                        }
                        preh = r.get(n)[1];
                        m++;
                        n++;
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    public static int minAreaRect(int[][] points) {
        List<int[]> pt = new ArrayList<>();
        int n = points.length, i;
        Map<Integer, Integer> xCnt = new HashMap<>();
        Map<Integer, Integer> yCnt = new HashMap<>();
        for (i = 0; i < n; i++) {
            xCnt.merge(points[i][0], 1, (oldVal, newVal) -> oldVal + newVal);
            yCnt.merge(points[i][1], 1, (oldVal, newVal) -> oldVal + newVal);
        }
        for (i = 0; i < n; i++) {
            deletePoints(points[i], xCnt, yCnt, pt);
        }
        delete(pt, xCnt, yCnt);
        return findMin(pt);
    }
    public static void main(String[] args) {
        int[][] points = new int[][]{
                {0,1},{1,2},{0,0},{3,0},
                {4,1},{1,4},{2,1},{4,2},
                {1,0},{3,4},{0,3}
        };
        int res = minAreaRect(points);
        System.out.println(res);
    }
}
