import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode51 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; // row - col + n - 1
        boolean[] diag2 = new boolean[2 * n - 1];
        for (int row = 0; row < n; row++) {
            Arrays.fill(board[row], '.');
        }
        nQueens(0, board, n, result, cols, diag1, diag2);
        return result;
    }

    private void nQueens(int row, char[][] board, int n, List<List<String>> result, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            result.add(constructBoard(board));
            return;
        }

        for (int i = 0; i < n; i++) {
            int d1 = row + i;
            int d2 = row - i + n - 1;
            if (cols[i] || diag1[d1] || diag2[d2]) continue;
            cols[i] = diag1[d1] = diag2[d2] = true;
            board[row][i] = 'Q';
            nQueens(row + 1, board, n, result, cols, diag1, diag2);
            cols[i] = diag1[d1] = diag2[d2] = false;
            board[row][i] = '.';
        }

    }

    public List<List<String>> solveNQueensOptimal(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int row = 0; row < n; row++) {
            Arrays.fill(board[row], '.');
        }
        nQueensOptimal(0, board, n, result, 0, 0, 0);
        return result;
    }

    private void nQueensOptimal(int row, char[][] board, int n, List<List<String>> result, int cols, int diag1, int diag2) {
        if (row == n) {
            result.add(constructBoard(board));
            return;
        }
        int mask = (1 << n) - 1;
        int available = mask & ~(cols | diag1 | diag2);
        while (available != 0) {
            int pick = available & -available;
            available = available - pick;
            int col = Integer.numberOfTrailingZeros(pick);
            board[row][col] = 'Q';

            nQueensOptimal(row + 1, board, n, result, cols | pick, (diag1 | pick) << 1, (diag2 | pick) >> 1);

            board[row][col] = '.';
        }

    }

    private List<String> constructBoard(char[][] board) {
        List<String> path = new ArrayList<>();
        for (char[] res : board) {
            path.add(new String(res));
        }
        return path;
    }
}
