import java.util.*;

public class Main {
    public static int bfs(int[][] grid, Queue<int[]> rot, int freshNum) {
        int minutes = 0;
        int[] cur;
        int size = rot.size();
        boolean hasRot = false;
        while (size > 0) {
            cur = rot.poll();
            size--;
            if (cur[0] > 0 && grid[cur[0] - 1][cur[1]] == 1) {
                grid[cur[0] - 1][cur[1]] = 2;
                rot.add(new int[]{cur[0] - 1, cur[1]});
                hasRot = true;
                freshNum--;
            }
            if (cur[0] < grid.length - 1 && grid[cur[0] + 1][cur[1]] == 1) {
                grid[cur[0] + 1][cur[1]] = 2;
                rot.add(new int[]{cur[0] + 1, cur[1]});
                hasRot = true;
                freshNum--;
            }
            if (cur[1] > 0 && grid[cur[0]][cur[1] - 1] == 1) {
                grid[cur[0]][cur[1] - 1] = 2;
                rot.add(new int[]{cur[0], cur[1] - 1});
                hasRot = true;
                freshNum--;
            }
            if (cur[1] < grid[0].length - 1 && grid[cur[0]][cur[1] + 1] == 1) {
                grid[cur[0]][cur[1] + 1] = 2;
                rot.add(new int[]{cur[0], cur[1] + 1});
                hasRot = true;
                freshNum--;
            }
            if (size == 0) {
                if (hasRot) {
                    minutes++;
                    hasRot = false;
                }
                size = rot.size();
            }
        }
        return freshNum == 0 ? minutes : -1;
    }
    public static int orangesRotting(int[][] grid) {
        int rottenNum = 0, freshNum = 0, i, j;
        Queue<int[]> rot = new LinkedList<>();
        for (i = 0; i < grid.length; i++) {
            for (j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    freshNum++;
                } else if (grid[i][j] == 2) {
                    rottenNum++;
                    rot.add(new int[]{i, j});
                }
            }
        }
        if (rottenNum >= 0 && freshNum == 0) {
            return 0;
        }
        if (rottenNum == 0 && freshNum > 0) {
            return -1;
        }
        return bfs(grid, rot, freshNum);
    }
    public static void main(String[] args) {
        int[][] grid = new int[][]{{2,1,1}, {1,1,0}, {0,1,1}};
        int ans = orangesRotting(grid);
        System.out.println(ans);
    }
}
