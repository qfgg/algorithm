import java.util.*;

class TicTacToe {
    int[][] board;
    int size;
    int[][] rowCount;
    int[][] colCount;
    int[][] diagCount;
    TicTacToe(int n) {
        size = n;
        board = new int[n][n];
        rowCount = new int[2][n];
        colCount = new int[2][n];
        diagCount = new int[2][2];
    }
    int move(int row, int col, int player) {
        board[row][col] = player;
        if (++rowCount[player - 1][row] == size ||
                ++colCount[player - 1][col] == size) {
            return player;
        }
        if (row == col) {
            diagCount[player - 1][0]++;
        }
        if (row + col == size - 1) {
            diagCount[player - 1][1]++;
        }
        if (diagCount[player - 1][0] == size ||
                diagCount[player - 1][1] == size) {
            return player;
        }
        return 0;
    }
}

class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(3);
        System.out.println(game.move(0, 0, 1));
        System.out.println(game.move(0, 2, 2));
        System.out.println(game.move(2, 2, 1));
        System.out.println(game.move(1, 1, 2));
        System.out.println(game.move(2, 0, 1));
        System.out.println(game.move(1, 0, 2));
        System.out.println(game.move(2, 1, 1));
    }
}
