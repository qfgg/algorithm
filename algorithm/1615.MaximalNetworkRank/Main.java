import java.util.*;

class Main {
    public static int maximalNetworkRank(int n, int[][] roads) {
        int[] cityCount = new int[n];
        int[][] record = new int[n][n];
        int numOfRoads = roads.length;
        int i, j, sum, max = 0;
        for (i = 0; i < numOfRoads; i++) {
            cityCount[roads[i][0]]++;
            cityCount[roads[i][1]]++;
            record[roads[i][0]][roads[i][1]] = 1;
            record[roads[i][1]][roads[i][0]] = 1;
        }
        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                sum = cityCount[i] + cityCount[j] - record[i][j];
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int n = 4;
        int[][] roads = new int[][]{{0,1},{0,3},{1,2},{1,3}};
        int ret = maximalNetworkRank(n, roads);
        System.out.println(ret);
    }
}
