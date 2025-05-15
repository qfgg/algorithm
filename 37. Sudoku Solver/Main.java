import java.util.*;


class Solution {
    int[] num2bit;
    HashMap<Integer, Integer> bit2num;
    Solution() {
        num2bit = new int[9];
        bit2num = new HashMap<>();
        int i = 0, cur = 1;
        while (i < 9) {
            num2bit[i] = cur;
            bit2num.put(cur, i);
            cur = cur << 1;
            i++;
        }
    }
    private List<Integer> computeState(char[][] board, int[][] cell, int[] next) {
        int[] row = new int[9], col = new int[9], box = new int[9];
        int i, j, k, topleftY, topleftX;
        for (i = 0; i < 9; i++) {
            row[i] = 511;
            for (j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    row[i] = row[i] & (~num2bit[board[i][j] - '1']);
                }
            }
        }
        for (i = 0; i < 9; i++) {
            col[i] = 511;
            for (j = 0; j < 9; j++) {
                if (board[j][i] != '.') {
                    col[i] = col[i] & (~num2bit[board[j][i] - '1']);
                }
            }
        }
        for (i = 0; i < 9; i++) {
            box[i] = 511;
            topleftY = i / 3 * 3;
            topleftX = i % 3 * 3;
            for (j = topleftY; j < topleftY + 3; j++) {
                for (k = topleftX; k < topleftX + 3; k++) {
                    if (board[j][k] != '.') {
                        box[i] = box[i] & (~num2bit[board[j][k] - '1']);
                    }
                }
            }
        }
        List<Integer> emptyList = new ArrayList<>();
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    cell[i][j] = row[i] & col[j] & box[i / 3 * 3 + j / 3];
                    emptyList.add(i * 10 + j);
                    if (next[0] == -1) {
                        next[0] = i;
                        next[1] = j;
                        next[2] = cell[i][j];
                    }
                }
            }
        }
        next[3] = emptyList.size();
        return emptyList;
    }
    private void findAreaSolves(char[][] board, int[][] cell, int topX, int topY, int bottomX, int bottomY, List<Integer> solves) {
        int i, j, common, exclusive, n, other, self, y, x;
        List<Integer> empty = new ArrayList<>();
        for (i = topY; i <= bottomY; i++) {
            for (j = topX; j <= bottomX; j++) {
                if (board[i][j] == '.') {
                    empty.add(i * 10 + j);
                }
            }
        }
        n = empty.size();
        for (i = 0; i < n; i++) {
            common = 0;
            for (j = 0; j < n; j++) {
                if (j != i) {
                    other = empty.get(j);
                    common = common | cell[other / 10][other % 10];
                }
            }
            self = empty.get(i);
            y = self / 10;
            x = self % 10;
            exclusive = cell[y][x] & (~common);
            if (bit2num.containsKey(exclusive)) {
                solves.add(bit2num.get(exclusive) * 100 + y * 10 + x);
            }
        }
    }
    private List<Integer> findSolves(char[][] board, int[][] cell, List<Integer> emptyList) {
        List<Integer> solves = new ArrayList<>();
        int n = emptyList.size(), i, j, k, pos;
        for (k = 0; k < n; k++) {
            pos = emptyList.get(k);
            i = pos / 10;
            j = pos % 10;
            if (bit2num.containsKey(cell[i][j])) {
                solves.add(bit2num.get(cell[i][j]) * 100 + i * 10 + j);
            }
        }
        for (i = 0; i < 9; i++) {
            findAreaSolves(board, cell, 0, i, 8, i, solves);
        }
        for (i = 0; i < 9; i++) {
            findAreaSolves(board, cell, i, 0, i, 8, solves);
        }
        for (i = 0; i < 9; i++) {
            findAreaSolves(board, cell, i % 3 * 3, i / 3 * 3, i % 3 * 3 + 2, i / 3 * 3 + 2, solves);
        }
        return solves;
    }
    private void fill(char[][] board, List<Integer> solves) {
        int solve, i, n = solves.size(), val, y, x;
        for (i = 0; i < n; i++) {
            solve = solves.get(i);
            x = solve % 10;
            solve = solve / 10;
            y = solve % 10;
            solve = solve / 10;
            val = solve;
            board[y][x] = (char)('1' + val);
        }
    }
    private int[] solve(char[][] board) {
        int[][] cell;
        int[] next = new int[4];
        List<Integer> emptyList, solves;
        while (true) {
            cell = new int[9][9];
            next[0] = -1;
            emptyList = computeState(board, cell, next);
            solves = findSolves(board, cell, emptyList);
            if (solves.isEmpty()) {
                break;
            }
            fill(board, solves);
        }
        return next;
    }
    private boolean check(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        int i, j, bIdx;
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (rows[i][board[i][j] - '1']) {
                        return false;
                    }
                    rows[i][board[i][j] - '1'] = true;
                    if (cols[j][board[i][j] - '1']) {
                        return false;
                    }
                    cols[j][board[i][j] - '1'] = true;
                    bIdx = i / 3 * 3 + j / 3;
                    if (boxes[bIdx][board[i][j] - '1']) {
                        return false;
                    }
                    boxes[bIdx][board[i][j] - '1'] = true;
                }
            }
        }
        return true;
    }
    private char[][] dfs(char[][] boardState, int[] next) {
        if (next[2] == 0) {
            return null;
        }
        int y = next[0], x = next[1], i, j;
        int curState = next[2];
        int[] newNext;
        char[][] res = null;
        for (i = 0; i < 9; i++) {
            if ((curState & 1) == 1) {
                char[][] boardCopy = new char[9][];
                for (j = 0; j < 9; j++) {
                    boardCopy[j] = boardState[j].clone();
                }
                boardCopy[y][x] = (char)('1' + i);
                newNext = solve(boardCopy);
                boolean isValid = check(boardCopy);
                if (isValid) {
                    if (newNext[3] == 0) {
                        return boardCopy;
                    }
                    res = dfs(boardCopy, newNext);
                    if (res != null) {
                        break;
                    }
                }
            }
            curState = curState >> 1;
        }
        return res;
    }
    public void solveSudoku(char[][] board) {
        int[] next = solve(board);
        if (next[3] == 0) {
            return;
        }
        char[][] res = dfs(board, next);
        if (res != null) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') {
                        board[i][j] = res[i][j];
                    }
                }
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] board = {
                {'.','.','9','7','4','8','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'.','2','.','1','.','9','.','.','.'},
                {'.','.','7','.','.','.','2','4','.'},
                {'.','6','4','.','1','.','5','9','.'},
                {'.','9','8','.','.','.','3','.','.'},
                {'.','.','.','8','.','3','.','2','.'},
                {'.','.','.','.','.','.','.','.','6'},
                {'.','.','.','2','7','5','9','.','.'}
        };
        s.solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}
