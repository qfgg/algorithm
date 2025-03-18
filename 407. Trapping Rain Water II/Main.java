import java.util.*;

class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length, sum = 0, i;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> heightMap[a[0]][a[1]] - heightMap[b[0]][b[1]]);
        int[][] water = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        for (i = 0; i < n; i++) {
            heap.add(new int[]{0, i});
            water[0][i] = heightMap[0][i];
            visited[0][i] = true;
            heap.add(new int[]{m - 1, i});
            water[m - 1][i] = heightMap[m - 1][i];
            visited[m - 1][i] = true;
        }
        for (i = 1; i < m - 1; i++) {
            heap.add(new int[]{i, 0});
            water[i][0] = heightMap[i][0];
            visited[i][0] = true;
            heap.add(new int[]{i, n - 1});
            water[i][n - 1] = heightMap[i][n - 1];
            visited[i][n - 1] = true;
        }
        int ny, nx;
        int[] dir = new int[]{0, -1, 0, 1, 0};
        int[] cur;
        while (!heap.isEmpty()) {
            cur = heap.poll();
            for (i = 0; i < 4; i++) {
                ny = cur[0] + dir[i];
                nx = cur[1] + dir[i + 1];
                if (ny > 0 && ny < m - 1 && nx > 0 && nx < n - 1 && !visited[ny][nx]) {
                    heap.add(new int[]{ny, nx});
                    water[ny][nx] = Math.max(water[cur[0]][cur[1]], heightMap[ny][nx]);
                    visited[ny][nx] = true;
                    if (water[ny][nx] > heightMap[ny][nx]) {
                        sum += water[ny][nx] - heightMap[ny][nx];
                    }
                }
            }
        }
        return sum;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] heightMap1 = {
                {12,13,1,12},
                {13,4,13,12},
                {13,8,10,12},
                {12,13,12,12},
                {13,13,13,13},
        }; // 14
        int res1 = s.trapRainWater(heightMap1);
        System.out.println(res1);
        int[][] heightMap2 = {
                {5,5,5,1},
                {5,1,1,5},
                {5,1,5,5},
                {5,2,5,8}
        }; // 3
        int res2 = s.trapRainWater(heightMap2);
        System.out.println(res2);
        int[][] heightMap3 = {
                {78,16,94,36},
                {87,93,50,22},
                {63,28,91,60},
                {64,27,41,27},
                {73,37,12,69},
                {68,30,83,31},
                {63,24,68,36}
        }; // 44
        int res3 = s.trapRainWater(heightMap3);
        System.out.println(res3);
        int[][] heightMap4 = {
                {9,9,9,9,9,9,8,9,9,9,9},
                {9,0,0,0,0,0,1,0,0,0,9},
                {9,0,0,0,0,0,0,0,0,0,9},
                {9,0,0,0,0,0,0,0,0,0,9},
                {9,9,9,9,9,9,9,9,9,9,9}
        }; // 215
        int res4 = s.trapRainWater(heightMap4);
        System.out.println(res4);
        int[][] heightMap5 = {
                {5,8,7,7},
                {5,2,1,5},
                {7,1,7,1},
                {8,9,6,9},
                {9,8,9,9}
        }; // 12
        int res5 = s.trapRainWater(heightMap5);
        System.out.println(res5);
    }
}
