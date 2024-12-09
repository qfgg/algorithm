import java.util.*;

class Main {
    public static boolean findNextOne(char[][] grid, int[][] visited, int[] pos) {
        for (int i = pos[0]; i < grid.length; i++) {
            for (int j = i == pos[0] ? pos[1] : 0; j < grid[i].length; j++) {
                if (visited[i][j] == 0 && grid[i][j] == '1') {
                    pos[0] = i;
                    pos[1] = j;
                    return true;
                }
            }
        }
        return false;
    }
    public static void visitIsland(char[][] grid, int[][] visited, int x, int y) {
        visited[y][x] = 1;
        if (y >= 1 && visited[y - 1][x] == 0 && grid[y - 1][x] == '1') {
            visitIsland(grid, visited, x, y - 1);
        }
        if (x >= 1 && visited[y][x - 1] == 0 && grid[y][x - 1] == '1') {
            visitIsland(grid, visited, x - 1, y);
        }
        if (x < grid[0].length - 1 && visited[y][x + 1] == 0 && grid[y][x + 1] == '1') {
            visitIsland(grid, visited, x + 1, y);
        }
        if (y < grid.length - 1 && visited[y + 1][x] == 0 && grid[y + 1][x] == '1') {
            visitIsland(grid, visited, x, y + 1);
        }
    }
    public static int numIslands(char[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        int count = 0;
        int[] pos = new int[2];
        while (findNextOne(grid, visited, pos)){
            count++;
            visitIsland(grid, visited, pos[1], pos[0]);
        }
        return count;
    }
    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'0','1','0'},
                {'1','0','1'},
                {'0','1','0'},
        };
        int num = numIslands(grid);
        System.out.println(num);
    }
}
