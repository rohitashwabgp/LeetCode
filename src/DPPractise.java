import java.util.Arrays;

public class DPPractise {

    private static int maximumValue(int[] weights, int[] values, int W) {

        int[] dp = new int[W + 1];
        int n = values.length;
        for (int i = 0; i < n; i++) {
            for (int w = W; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], values[i] + dp[w - weights[i]]);
            }
        }
        return dp[W];
    }

    private static boolean subsetSum(int[] values, int target) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int value : values) {
            for (int j = target; j >= value; j--) {
                dp[j] = (dp[j - value] || dp[j]);
            }
        }
        return dp[target];
    }

    private static boolean PartitionSubsetSum(int[] values) {
        int target = Arrays.stream(values).sum();
        if (target % 2 != 0) return false;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int value : values) {
            for (int j = target; j >= value; j--) {
                dp[j] = (dp[j - value] || dp[j]);
            }
        }
        return dp[target];
    }

    private static boolean coins(int[] coins, int amount) {
        boolean[] dp = new boolean[amount + 1];
        dp[0] = true;
        for (int value : coins) {
            for (int j = value; j <= amount; j++) {
                dp[j] = dp[j] || dp[j - value];
            }
        }
        return dp[amount];
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        if (original == color) return image;

        for (int row = 0; row < image.length; row++) {
            for (int col = 0; col < image[0].length; col++) {
                if (image[row][col] == original)
                    updateColor(image, row, col, color, original);
            }
        }
        return image;
    }

    private void updateColor(int[][] image, int row, int col, int color, int original) {
        if (row < 0 || col < 0 || row >= image.length || col >= image[0].length || image[row][col] != original) {
            return;
        }

        image[row][col] = color;
        updateColor(image, row + 1, col, color, original);
        updateColor(image, row - 1, col, color, original);
        updateColor(image, row, col + 1, color, original);
        updateColor(image, row, col - 1, color, original);
    }

    public boolean exist(char[][] board, String word) {
        if (word.isEmpty()) return true;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == word.charAt(0) && verify(board, 0, row, col, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verify(char[][] board, int idx, int row, int col, String word) {
        if (idx == word.length()) return true;
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != word.charAt(idx)) {
            return false;
        }
        char temp = board[row][col];
        board[row][col] = '#';
        boolean found = verify(board, idx + 1, row + 1, col, word) ||
                    verify(board, idx + 1, row - 1, col, word) ||
                    verify(board, idx + 1, row, col + 1, word) ||
                    verify(board, idx + 1, row, col - 1, word);

        board[row][col] = temp;
        return found;
    }
}
