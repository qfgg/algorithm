import java.util.*;

class DetectSquares {
    int[][] points;
    Map<Integer, Set<Integer>> x2y;
    Map<Integer, Set<Integer>> y2x;
    public DetectSquares() {
        points = new int[1001][1001];
        x2y = new HashMap<>();
        y2x = new HashMap<>();
    }

    public void add(int[] point) {
        points[point[1]][point[0]]++;
        if (x2y.containsKey(point[0])) {
            x2y.get(point[0]).add(point[1]);
        } else {
            x2y.put(point[0], new HashSet<>(Arrays.asList(point[1])));
        }
        if (y2x.containsKey(point[1])) {
            y2x.get(point[1]).add(point[0]);
        } else {
            y2x.put(point[1], new HashSet<>(Arrays.asList(point[0])));
        }
    }

    public int count(int[] point) {
        if (!x2y.containsKey(point[0]) || !y2x.containsKey(point[1])) {
            return 0;
        }
        Set<Integer> yset = x2y.get(point[0]);
        Set<Integer> xset = y2x.get(point[1]);
        int cnt = 0, l, x1, x2;
        for (int y : yset) {
            if (y == point[1]) {
                continue;
            }
            l = Math.abs(y - point[1]);
            x1 = point[0] + l;
            x2 = point[0] - l;
            if (x1 <= 1000 && xset.contains(x1) && points[y][x1] > 0) {
                cnt += points[y][x1] * points[y][point[0]] * points[point[1]][x1];
            }
            if (x2 >= 0 && xset.contains(x2) && points[y][x2] > 0) {
                cnt += points[y][x2] * points[y][point[0]] * points[point[1]][x2];
            }
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        System.out.println(detectSquares.count(new int[]{11, 10})); // return 1. You can choose:
        //   - The first, second, and third points
        System.out.println(detectSquares.count(new int[]{14, 8}));  // return 0. The query point cannot form a square with any points in the data structure.
        detectSquares.add(new int[]{11, 2});    // Adding duplicate points is allowed.
        System.out.println(detectSquares.count(new int[]{11, 10})); // return 2. You can choose:
        //   - The first, second, and third points
        //   - The first, third, and fourth points
    }
}
