import java.util.*;

class Main {
    public static List<Integer> spiralOrder(int[][] matrix) {
        int yLen = matrix.length;
        int xLen = matrix[0].length;
        int[][] visited = new int[yLen][xLen];
        int[][] offsets = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        List<Integer> ret = new ArrayList<>();
        int x = 0;
        int y = 0;
        int nextX;
        int nextY;
        int curDirection = 0;
        while (true) {
            ret.add(matrix[y][x]);
            visited[y][x] = 1;
            nextX = x + offsets[curDirection][0];
            nextY = y + offsets[curDirection][1];
            if (nextX >= 0 && nextX < xLen && nextY >= 0 && nextY < yLen && visited[nextY][nextX] != 1) {
                x = nextX;
                y = nextY;
                continue;
            }
            curDirection = (curDirection + 1) % 4;
            nextX = x + offsets[curDirection][0];
            nextY = y + offsets[curDirection][1];
            if (nextX < 0 || nextX == xLen || nextY < 0 || nextY == yLen || visited[nextY][nextX] == 1) {
                return ret;
            }
            x = nextX;
            y = nextY;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9},
        };
        List<Integer> ret = spiralOrder(matrix);
        System.out.println(ret.toString());
    }
}
