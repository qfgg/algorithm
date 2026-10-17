import java.util.*;


class Solution {
    void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int tmp = board[y1][x1];
        board[y1][x1] = board[y2][x2];
        board[y2][x2] = tmp;
    }
    void flipY(int[][] board, int n) {
        int i = 0, j = n - 1, k;
        while (i < j) {
            for (k = 0; k < n; k++) {
                swap(board, k, i, k, j);
            }
            i++;
            j--;
        }
    }
    void flipX(int[][] board, int n) {
        int i, j, k;
        for (k = 1; k < n; k += 2) {
            i = 0;
            j = n - 1;
            while (i < j) {
                swap(board, i, k, j, k);
                i++;
                j--;
            }
        }
    }
    public int snakesAndLadders(int[][] board) {
        int n = board.length, m = n * n, k, next, ny, nx;
        flipY(board, n);
        flipX(board, n);
        int[] dist = new int[m];
        boolean[] done = new boolean[m];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        q.add(new int[]{0, 0});
        int[] cur;
        while (!q.isEmpty()) {
            cur = q.poll();
            while (!q.isEmpty() && cur[1] != dist[cur[0]]) {
                cur = q.poll();
            }
            done[cur[0]] = true;
            for (k = 1; k <= 6 && cur[0] + k < m; k++) {
                next = cur[0] + k;
                ny = next / n;
                nx = next % n;
                if (board[ny][nx] > 0) {
                    next = board[ny][nx] - 1;
                }
                if (cur[1] + 1 < dist[next]) {
                    dist[next] = cur[1] + 1;
                    q.add(new int[]{next, cur[1] + 1});
                }
            }
        }
        return dist[m - 1] == Integer.MAX_VALUE ? -1 : dist[m - 1];
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[][] board = new int[][]{
          {-1,-1,-1,-1,-1,-1},
          {-1,-1,-1,-1,-1,-1},
          {-1,-1,-1,-1,-1,-1},
          {-1,35,-1,-1,13,-1},
          {-1,-1,-1,-1,-1,-1},
          {-1,15,-1,-1,-1,-1},
      };
      int res = s.snakesAndLadders(board);
      System.out.println(res);
  }
}

