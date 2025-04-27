import java.util.*;


class Solution {
    private void bfs(char[][] board, int rn, int cn, int r, int c, char source, char target) {
        int[] dir = new int[]{0, -1, 0, 1, 0};
        int nr, nc, cur, curr, curc, i;
        Queue<Integer> q = new LinkedList<>();
        q.add(r * 1000 + c);
        board[r][c] = target;
        while (!q.isEmpty()) {
            cur = q.poll();
            curr = cur / 1000;
            curc = cur % 1000;
            for (i = 0; i < 4; i ++) {
                nr = curr + dir[i];
                nc = curc + dir[i + 1];
                if (nr >= 0 && nr < rn && nc >= 0 && nc < cn && board[nr][nc] == source) {
                    board[nr][nc] = target;
                    q.add(nr * 1000 + nc);
                }
            }
        }
    }
    private void markOutside(char[][] board, int rn, int cn, char source, char target) {
        int i;
        for (i = 0; i < rn; i++) {
            if (board[i][0] == source) {
                bfs(board, rn, cn, i, 0, source, target);
            }
            if (board[i][cn - 1] == source) {
                bfs(board, rn, cn, i, cn - 1, source, target);
            }
        }
        for (i = 1; i < cn - 1; i++) {
            if (board[0][i] == source) {
                bfs(board, rn, cn, 0, i, source, target);
            }
            if (board[rn - 1][i] == source) {
                bfs(board, rn, cn, rn - 1, i, source, target);
            }
        }
    }
    private void markInside(char[][] board, int rn, int cn) {
        int i, j;
        for (i = 0; i < rn; i++) {
            for (j = 0; j < cn; j++) {
                if (board[i][j] == 'O') {
                    bfs(board, rn, cn, i, j, 'O','X');
                }
            }
        }
    }
    public void solve(char[][] board) {
        int rn = board.length, cn = board[0].length;
        markOutside(board, rn, cn, 'O', '#');
        markInside(board, rn, cn);
        markOutside(board, rn, cn, '#', 'O');
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] board = {
                {'O','O'},
                {'O','O'},
        };
        s.solve(board);
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}
