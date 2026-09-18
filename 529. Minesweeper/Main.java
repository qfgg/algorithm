import java.util.*;


class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int y = click[0], x = click[1];
        if (board[y][x] == 'M') {
            board[y][x] = 'X';
            return board;
        }
        int[] dir = new int[]{-1,0,-1,-1,1,0,1,1,-1};
        int i, ny, nx, m = board.length, n = board[0].length, cnt;
        if (board[y][x] == 'E') {
            cnt = 0;
            for (i = 0; i < 8; i++) {
                ny = y + dir[i];
                nx = x + dir[i + 1];
                if (ny >= 0 && ny < m && nx >= 0 && nx < n) {
                    if (board[ny][nx] == 'M') {
                        cnt++;
                    }
                }
            }
            if (cnt > 0) {
                board[y][x] = (char)('0' + cnt);
                return board;
            }
            board[y][x] = 'B';
            for (i = 0; i < 8; i++) {
                ny = y + dir[i];
                nx = x + dir[i + 1];
                if (ny >= 0 && ny < m && nx >= 0 && nx < n && board[ny][nx] != 'B') {
                    updateBoard(board, new int[]{ny, nx});
                }
            }
        }
        return board;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      char[][] board = new char[][]{
              {'E','E','E','E','E'},
              {'E','E','M','E','E'},
              {'E','E','E','E','E'},
              {'E','E','E','E','E'}
      };
      int[] click = new int[]{3, 0};
      char[][] ret = s.updateBoard(board, click);
      System.out.println(Arrays.deepToString(ret));
  }
}
