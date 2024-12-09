import java.util.*;

public class Main {
    public static int countBattleships(char[][] board) {
        int rn = board.length, cn = board[0].length, cnt = 0;
        int n = (int) Math.ceil((double)cn / 60), i, j, upStart, downStart;
        long[] masks = new long[n];
        long[] nMasks = new long[n];
        long[] tmp;
        for (i = 1; i < rn; i++) {
            upStart = -1;
            downStart = -1;
            for (j = 0; j < cn; j++) {
                if (board[i - 1][j] == 'X') {
                    if (upStart == -1) {
                        upStart = j;
                    }
                    if (board[i][j] == 'X') {
                        if (downStart == -1 && (masks[upStart / 60] & (1L << (upStart % 60))) > 0) {// up row Xs invalid
                            downStart = j;
                            nMasks[j / 60] |= (1L << j % 60);
                        } else if (downStart == -1 && j > upStart) { // current row's X starts later than up row
                            downStart = j;
                            masks[upStart / 60] |= (1L << upStart % 60);
                            nMasks[j / 60] |= (1L << j % 60);
                        } else if (downStart > -1 && downStart < upStart) {// current row's X starts earlier than up row
                            masks[upStart / 60] |= (1L << upStart % 60);
                            nMasks[downStart / 60] |= (1L << downStart % 60);
                        } else if (j == upStart) {
                            downStart = j;
                            masks[upStart / 60] |= (1L << upStart % 60); // no matter valid or not, shouldn't count yet
                            if (j < cn - 1 &&                      // current row's X and up row starts at the same time
                                    (board[i - 1][j + 1] == 'X' ||   // and current row or up row has over 1 X
                                            board[i][j + 1] == 'X')) {
                                nMasks[j / 60] |= (1L << j % 60);
                            }
                        }
                    } else {
                        downStart = -1;
                    }
                    if (j == cn - 1 && (masks[upStart / 60] & (1L << (upStart % 60))) == 0) {
                        cnt++;
                    }
                } else {
                    if (upStart > -1) {
                        if ((masks[upStart / 60] & (1L << (upStart % 60))) == 0) { // end of valid Xs in up row
                            cnt++;
                        }
                        upStart = -1;
                        downStart = -1;
                    }
                    if (board[i][j] == 'X') {
                        if (downStart == -1) {
                            downStart = j;
                        }
                    } else {
                        downStart = -1;
                    }
                }
            }
            tmp = masks;
            masks = nMasks;
            nMasks = tmp;
            Arrays.fill(nMasks, 0);
        }
        upStart = -1;
        for (j = 0; j < cn; j++) {
            if (board[rn - 1][j] == 'X') {
                if (upStart == -1) {
                    upStart = j;
                    if ((masks[upStart / 60] & (1L << (upStart % 60))) == 0) {
                        cnt++;
                    }
                }
            } else if (upStart > -1) {
                upStart = -1;
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X','X','X'},
                {'.','X','.'},
                {'.','X','.'},
        };
        int res = countBattleships(board);
        System.out.println(res);
    }
}
