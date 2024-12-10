import java.util.*;

public class Main {
    public static boolean removeOnes(int[][] grid) {
        int rn = grid.length, cn = grid[0].length;
        boolean isOppo;
        for (int i = 1; i < rn; i++) {
            isOppo = grid[i][0] != grid[0][0];
            for (int j = 1; j < cn; j++) {
                if ((isOppo && grid[i][j] == grid[0][j]) ||
                        (!isOppo && grid[i][j] != grid[0][j])) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,1,0}, {1,0,1}, {0,1,0}};
        boolean ans = removeOnes(grid);
        System.out.println(ans);
    }
}
