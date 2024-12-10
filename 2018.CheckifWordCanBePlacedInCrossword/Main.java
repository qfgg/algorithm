import java.util.*;

public class Main {
    public static boolean placeWordInCrossword(char[][] board, String word) {
        int rn = board.length, cn = board[0].length, wn = word.length(), i , j, dir;
        int[][][] f = new int[rn][cn][3];
        for(i = 0; i < rn; i++) {
            for(j = 0; j < cn; j++) {
                if (board[i][j] == '#') {
                    continue;
                }
                if (j == 0 || board[i][j - 1] == '#') {
                    dir = 0;
                    if (board[i][j] == word.charAt(0)) {
                        dir |= 1;
                    }
                    if (board[i][j] == word.charAt(wn - 1)) {
                        dir |= 2;
                    }
                    if (board[i][j] == ' ') {
                        dir |= 3;
                    }
                    if (dir > 0) {
                        f[i][j][0] |= dir;
                        if (wn == 1 && (j == cn - 1 || board[i][j + 1] == '#')) {
                            return true;
                        }
                    }
                }
                if (i == 0 || board[i - 1][j] == '#') {
                    dir = 0;
                    if (board[i][j] == word.charAt(0)) {
                        dir |= 4;
                    }
                    if (board[i][j] == word.charAt(wn - 1)) {
                        dir |= 8;
                    }
                    if (board[i][j] == ' ') {
                        dir |= 12;
                    }
                    if (dir > 0) {
                        f[i][j][0] |= dir;
                        if (wn == 1 && (i == rn - 1 || board[i + 1][j] == '#')) {
                            return true;
                        }
                    }
                }
                if (j > 0 && f[i][j - 1][0] > 0) {
                    dir = 0;
                    if ((f[i][j - 1][0] & 1) == 1) {
                        dir |= f[i][j - 1][1] + 1 < wn &&
                                (board[i][j] == ' ' ||
                                        board[i][j] == word.charAt(f[i][j - 1][1] + 1)) ? 1 : 0;
                    }
                    if ((f[i][j - 1][0] & 2) == 2) {
                        dir |= wn - f[i][j - 1][1] - 2 >= 0 &&
                                (board[i][j] == ' ' ||
                                        board[i][j] == word.charAt(wn - f[i][j - 1][1] - 2)) ? 2 : 0;
                    }
                    if (dir > 0) {
                        f[i][j][0] |= dir;
                        f[i][j][1] = f[i][j - 1][1] + 1;
                        if (wn == f[i][j][1] + 1 && (j == cn - 1 || board[i][j + 1] == '#')) {
                            return true;
                        }
                    }
                }
                if (i > 0 && f[i - 1][j][0] > 0) {
                    dir = 0;
                    if ((f[i - 1][j][0] & 4) == 4) {
                        dir |= f[i - 1][j][2] + 1 < wn &&
                                (board[i][j] == ' ' ||
                                        board[i][j] == word.charAt(f[i - 1][j][2] + 1)) ? 4 : 0;
                    }
                    if ((f[i - 1][j][0] & 8) == 8) {
                        dir |= wn - f[i - 1][j][2] - 2 >= 0 &&
                                (board[i][j] == ' ' ||
                                        board[i][j] == word.charAt(wn - f[i - 1][j][2] - 2)) ? 8 : 0;
                    }
                    if (dir > 0) {
                        f[i][j][0] |= dir;
                        f[i][j][2] = f[i - 1][j][2] + 1;
                        if (wn == f[i][j][2] + 1 && (i == rn - 1 || board[i + 1][j] == '#')) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'#', ' ', '#'},
                {'#', ' ', '#'},
                {'#', ' ', 'c'},
        };
        String word = "ca";
        boolean res = placeWordInCrossword(board, word);
        System.out.println(res);
    }
}
