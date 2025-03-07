import java.util.*;

class Solution {
    private void populateRealHeight(int y, int x, int h, int[][] heightMap, int[][] dp) {
        int[] offset = new int[]{0, 1, 0, -1, 0};
        Queue<int[]> q = new LinkedList<>();
        int[] cur = new int[]{y, x};
        int k, ny, nx;
        q.add(cur);
        while(!q.isEmpty()) {
            cur = q.poll();
            dp[cur[0]][cur[1]] = h;
            for (k = 0; k < 4; k++) {
                ny = cur[0] + offset[k];
                nx = cur[1] + offset[k + 1];
                if (heightMap[ny][nx] < dp[ny][nx] && h < dp[ny][nx]) {
                    if (h > heightMap[ny][nx]) {
                        q.add(new int[]{ny, nx});
                    } else {
                        populateRealHeight(ny, nx, heightMap[ny][nx], heightMap, dp);
                    }
                }
            }
        }
    }
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length, sum = 0, i, j, max = 0;
        int[][] dp = new int[m][n];
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (j == 0) {
                    max = heightMap[i][j];
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = max;
                    if (heightMap[i][j] > max) {
                        max = heightMap[i][j];
                    }
                }
            }
        }
        for (i = 0; i < m; i++) {
            for (j = n - 1; j >= 0; j--) {
                if (j == n - 1) {
                    max = heightMap[i][j];
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i][j], max);
                    if (heightMap[i][j] > max) {
                        max = heightMap[i][j];
                    }
                }
            }
        }
        for (j = 0; j < n; j++) {
            for (i = 0; i < m; i++) {
                if (i == 0) {
                    max = heightMap[i][j];
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i][j], max);
                    if (heightMap[i][j] > max) {
                        max = heightMap[i][j];
                    }
                }
            }
        }
        for (j = 0; j < n; j++) {
            for ( i = m - 1; i >= 0; i--) {
                if (i == m - 1) {
                    max = heightMap[i][j];
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i][j], max);
                    if (heightMap[i][j] > max) {
                        max = heightMap[i][j];
                    }
                }
            }
        }
        int[] offset = new int[]{0, 1, 0, -1, 0};
        int k, ny, nx, nMin;
        for (i = 1; i < m - 1; i++) {
            for (j = 1; j < n - 1; j++) {
                nMin = dp[i][j];
                for (k = 0; k < 4; k++) {
                    ny = i + offset[k];
                    nx = j + offset[k + 1];
                    if (dp[i][j] > heightMap[i][j] && dp[ny][nx] > 0) {
                        max = Math.max(dp[ny][nx], heightMap[ny][nx]);
                        if (max < nMin) {
                            nMin = max;
                        }
                    }
                }
                if (nMin < dp[i][j]) {
                    if (nMin < heightMap[i][j]) {
                        nMin = heightMap[i][j];
                    }
                    populateRealHeight(i, j, nMin, heightMap, dp);
                }
            }
        }
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (dp[i][j] > heightMap[i][j]) {
                    sum += dp[i][j] - heightMap[i][j];
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
        int[][] heightMap6 = {
                {19383,10886,12777,16915,17793,18335,15386,10492,16649,11421},
                {12362,27,8690,59,7763,3926,540,3426,9172,5736},
                {15211,5368,2567,6429,5782,1530,2862,5123,4067,3135},
                {13929,9802,4022,3058,3069,8167,1393,8456,5011,8042},
                {16229,7373,4421,4919,3784,8537,5198,4324,8315,4370},
                {16413,3526,6091,8980,9956,1873,6862,9170,6996,7281},
                {12305,925,7084,6327,336,6505,846,1729,1313,5857},
                {16124,3895,9582,545,8814,3367,5434,364,4043,3750},
                {11087,6808,7276,7178,5788,3584,5403,2651,2754,2399},
                {19932,5060,9676,3368,7739,12,6226,8586,8094,7539}
        }; // 79058
        int res6 = s.trapRainWater(heightMap6);
        System.out.println(res6);
    }
}
