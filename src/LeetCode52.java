public class LeetCode52 {

    private int count = 0;

    public int totalNQueens(int n) {
        backtrack(n, 0, 0, 0, 0);
        return count;
    }

    private void backtrack(int n, int row, int cols, int diag1, int diag2) {
        if (row == n) {
            count++;
            return;
        }

        int available = ((1 << n) - 1) & ~(cols | diag1 | diag2);

        while (available != 0) {
            int pick = available & -available; // rightmost available column
            available = available - pick;

            backtrack(n, row + 1, cols | pick, (diag1 | pick) << 1, (diag2 | pick) >> 1);
        }

    }
}
