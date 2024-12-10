import java.util.*;

public class Main {
    public static int orangesRotting(int[][] grid) {
        int freshNum = 0, i, j;
        Queue<int[]> rot = new LinkedList<>();
        int r = grid.length, c = grid[0].length;
        for (i = 0; i < r; i++) {
            for (j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    freshNum++;
                } else if (grid[i][j] == 2) {
                    rot.add(new int[]{i, j});
                }
            }
        }
        if (freshNum == 0) {
            return 0;
        }
        int minutes = 0;
        int[] cur;
        int size = rot.size();
        while (size > 0) {
            cur = rot.poll();
            size--;
            if (cur[0] > 0 && grid[cur[0] - 1][cur[1]] == 1) {
                grid[cur[0] - 1][cur[1]] = 2;
                rot.add(new int[]{cur[0] - 1, cur[1]});
                freshNum--;
            }
            if (cur[0] < r - 1 && grid[cur[0] + 1][cur[1]] == 1) {
                grid[cur[0] + 1][cur[1]] = 2;
                rot.add(new int[]{cur[0] + 1, cur[1]});
                freshNum--;
            }
            if (cur[1] > 0 && grid[cur[0]][cur[1] - 1] == 1) {
                grid[cur[0]][cur[1] - 1] = 2;
                rot.add(new int[]{cur[0], cur[1] - 1});
                freshNum--;
            }
            if (cur[1] < c - 1 && grid[cur[0]][cur[1] + 1] == 1) {
                grid[cur[0]][cur[1] + 1] = 2;
                rot.add(new int[]{cur[0], cur[1] + 1});
                freshNum--;
            }
            if (size == 0) {
                minutes++;
                size = rot.size();
            }
        }
        return freshNum == 0 ? minutes - 1 : -1;
    }
    public static void main(String[] args) {
        int[][] grid = new int[][]{{2,1,1}, {1,1,0}, {0,1,1}};
        int ans = orangesRotting(grid);
        System.out.println(ans);
    }
}
