import java.util.*;


public class Main {
    public static boolean dfs(char[][] board, String word, int index, int row, int col) {
        if (index == word.length()) {
            return true;
        }
        char cur = word.charAt(index);
        if (row > 0 && board[row - 1][col] == cur) {
            board[row - 1][col] = '$';
            if (dfs(board, word, index + 1, row - 1, col)) {
                return true;
            }
            board[row - 1][col] = cur;
        }
        if (col > 0 && board[row][col - 1] == cur) {
            board[row][col - 1] = '$';
            if (dfs(board, word, index + 1, row, col - 1)) {
                return true;
            }
            board[row][col - 1] = cur;
        }
        if (row < board.length - 1 && board[row + 1][col] == cur) {
            board[row + 1][col] = '$';
            if (dfs(board, word, index + 1, row + 1, col)) {
                return true;
            }
            board[row + 1][col] = cur;
        }
        if (col < board[0].length - 1 && board[row][col + 1] == cur) {
            board[row][col + 1] = '$';
            if (dfs(board, word, index + 1, row, col + 1)) {
                return true;
            }
            board[row][col + 1] = cur;
        }
        return false;
    }
    public static boolean exist(char[][] board, String word) {
        int wordLen = word.length();
        if (wordLen > board.length * board[0].length) {
            return false;
        }
        int[] lowermap = new int[26];
        int[] uppermap = new int[26];
        for (char[] row : board) {
            for (char c : row) {
                if (c >= 'a' && c <= 'z' && lowermap[c - 'a'] == 0) {
                    lowermap[c - 'a'] = 1;
                } else if (c >= 'A' && c <= 'Z' && uppermap[c - 'A'] == 0) {
                    uppermap[c - 'A'] = 1;
                }
            }
        }
        char cur;
        for (int i = 0; i < wordLen; i++) {
            cur = word.charAt(i);
            if ((cur >= 'a' && cur <= 'z' && lowermap[cur - 'a'] == 0) ||
                    (cur >= 'A' && cur <= 'Z' && uppermap[cur - 'A'] == 0)) {
                return false;
            }
        }
        cur = word.charAt(0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == cur) {
                    board[i][j] = '$';
                    if (dfs(board, word, 1, i, j)) {
                        return true;
                    }
                    board[i][j] = cur;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        boolean ans = exist(board, word);
        System.out.println(ans);
    }
}
