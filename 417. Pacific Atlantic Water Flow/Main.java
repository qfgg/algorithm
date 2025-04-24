import java.util.*;


class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int r = heights.length, c = heights[0].length, i, j;
        if (r == 1 || c == 1) {
            for (i = 0; i < r; i++) {
                for (j = 0; j < c; j++) {
                    res.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
            return res;
        }
        HashSet<Integer> watered1 = new HashSet<>();
        HashSet<Integer> watered2 = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        int x, y, nx, ny, cur, next;
        int[] dir = new int[]{0, 1, 0, -1, 0};
        for (i = 0; i < c; i++) {
            cur = i;
            watered1.add(cur);
            q.add(cur);
        }
        for (i = 1; i < r; i++) {
            cur = 1000 * i;
            watered1.add(cur);
            q.add(cur);
        }
        while(!q.isEmpty()) {
            cur = q.poll();
            x = cur % 1000;
            y = cur / 1000;
            for (i = 0; i < 4; i++) {
                ny = y + dir[i];
                nx = x + dir[i + 1];
                next = (ny * 1000) + nx;
                if (!watered1.contains(next) &&
                        ny >= 0 &&
                        ny < r &&
                        nx >= 0 &&
                        nx < c &&
                        heights[y][x] <= heights[ny][nx]
                ) {
                    watered1.add(next);
                    q.add(next);
                }
            }
        }
        for (i = 0; i < c; i++) {
            cur = (r - 1) * 1000 + i;
            watered2.add(cur);
            q.add(cur);
        }
        for (i = 0; i < r - 1; i++) {
            cur = 1000 * i + c - 1;
            watered2.add(cur);
            q.add(cur);
        }
        while(!q.isEmpty()) {
            cur = q.poll();
            x = cur % 1000;
            y = cur / 1000;
            if (watered1.contains(cur)) {
                res.add(new ArrayList<>(Arrays.asList(y, x)));
            }
            for (i = 0; i < 4; i++) {
                ny = y + dir[i];
                nx = x + dir[i + 1];
                next = (ny * 1000) + nx;
                if (!watered2.contains(next) &&
                        ny >= 0 &&
                        ny < r &&
                        nx >= 0 &&
                        nx < c &&
                        heights[y][x] <= heights[ny][nx]
                ) {
                    watered2.add(next);
                    q.add(next);
                }
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] heights = new int[][]{
                {1,2,3},
                {8,9,4},
                {7,6,5},
        };
        List<List<Integer>> res = s.pacificAtlantic(heights);
        System.out.println(res);
    }
}
