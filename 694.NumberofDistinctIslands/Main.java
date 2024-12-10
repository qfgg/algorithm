import java.util.*;


public class Main {
    public static void dfs(int[][] grid, int r, int c, int rOffset, int cOffset, StringBuilder key) {
        grid[r][c] = 2;
        key.append(r - rOffset);
        key.append('#');
        key.append(c - cOffset);
        key.append('#');
        int rowMax = grid.length;
        int colMax = grid[0].length;
        int[] dir = new int[]{0, 1, 0, -1, 0};
        int cur, nr, nc;
        for (cur = 0; cur < 4; cur++) {
            nr = r + dir[cur];
            nc = c + dir[cur + 1];
            if (nr >= 0 && nr < rowMax && nc >= 0 && nc < colMax && grid[nr][nc] == 1) {
                dfs(grid, nr, nc, rOffset, cOffset, key);
            }
        }
    }
    public static int numDistinctIslands(int[][] grid) {
        Set<String> islandSet = new HashSet<>();
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, i, j, key);
                    islandSet.add(key.toString());
                    key.setLength(0);
                }
            }
        }
        return islandSet.size();
    }
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,1,0,1,1},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {1,1,0,1,1}
        };
        int ans = numDistinctIslands(grid);
        System.out.println(ans);
    }
}
