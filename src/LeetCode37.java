public class LeetCode37 {
    private final int[] rows = new int[9];
    private final int[] cols = new int[9];
    private final int[] boxes = new int[9];

    public void solveSudoku(char[][] board) {
        // Initialize masks
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    int digit = board[r][c] - '1';
                    int bit = 1 << digit;
                    rows[r] = rows[r] | bit;
                    cols[c] = cols[c] | bit;
                    boxes[boxIndex(r, c)] = boxes[boxIndex(r, c)] | bit;
                }
            }
        }
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int r, int c) {
        if (r == board.length) return true;
        if (c == board[0].length) return backtrack(board, r + 1, 0);
        int boxIndex = boxIndex(r, c);
        if (board[r][c] != '.') return backtrack(board, r, c + 1);
        int used = rows[r] | cols[c] | boxes[boxIndex];
        for (int d = 0; d < 9; d++) {
            int bit = (1 << d);
            if ((used & bit) != 0) continue;
            board[r][c] = (char) ('1' + d);
            rows[r] = rows[r] | bit;
            cols[c] = cols[c] | bit;
            boxes[boxIndex] = boxes[boxIndex] | bit;
            if (backtrack(board, r, c + 1)) return true;
            board[r][c] = '.';
            rows[r] = rows[r] ^ bit;
            cols[c] = cols[c] ^ bit;
            boxes[boxIndex] = boxes[boxIndex] ^ bit;
        }
        return false;
    }

    private int boxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }
}
