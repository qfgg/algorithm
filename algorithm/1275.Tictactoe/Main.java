class Main {
    public static String tictactoe(int[][] moves) {
        int[][] row = new int[2][3];
        int[][] col = new int[2][3];
        int[][] diag = new int[2][2];
        for (int i = 0; i < moves.length; i++) {
            int j = i % 2;
            if (++row[j][moves[i][0]] == 3 ||
                    ++col[j][moves[i][1]] == 3 ||
                    (moves[i][0] == moves[i][1] && ++diag[j][0] == 3) ||
                    (moves[i][0] + moves[i][1] == 2 && ++diag[j][1] == 3)) {
                return j == 0 ? "A" : "B";
            }
        }
        if (moves.length < 9) {
            return "Pending";
        }
        return "Draw";
    }
    public static void main(String[] args) {
        int[][] moves = new int[][] {{0, 0}, {2, 0}, {1, 1}, {2, 1}, {2, 2}};
        System.out.println(tictactoe(moves));
    }
}

