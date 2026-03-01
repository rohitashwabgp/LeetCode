public class LeetCode36 {
    public boolean isValidSudoku(char[][] board) {

        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                char ch = board[r][c];
                if (ch == '.') continue;

                int digit = ch - '1';      // 0..8
                int mask = 1 << digit;
                int boxIndex = (r / 3) * 3 + (c / 3);

                // check duplicates
                if ((rows[r] & mask) != 0 || (cols[c] & mask) != 0 || (boxes[boxIndex] & mask) != 0) {
                    return false;
                }

                // mark digit as seen
                rows[r] |= mask;
                cols[c] |= mask;
                boxes[boxIndex] |= mask;
            }
        }
        return true;
    }
}
