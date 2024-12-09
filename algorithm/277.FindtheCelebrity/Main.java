import java.util.*;




public class Main {
    public static int[][] graph = new int[][]{
            {1,1,0,1,0,1,1},
            {1,1,0,0,0,1,0},
            {1,1,1,1,0,1,1},
            {0,1,0,1,0,1,1},
            {0,0,0,1,1,1,1},
            {0,0,0,0,0,1,0},
            {1,0,0,0,0,1,1},
    };
    public static boolean knows(int a, int b) {
        return graph[a][b] == 1;
    }
    public static int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (candidate != i && (!knows(i, candidate) || knows(candidate, i))) {
                return -1;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        int ans = findCelebrity(7);
        System.out.println(ans);
    }
}
