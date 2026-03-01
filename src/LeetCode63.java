public class LeetCode63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        Integer[][] memo = new Integer[m][n];
        return dfs(obstacleGrid, m - 1, n - 1, memo);
    }

    private int dfs(int[][] grid, int r, int c, Integer[][] memo) {
        if (r < 0 || c < 0 || grid[r][c] == 1) {
            return 0;
        }
        if (r == 0 && c == 0) {
            return 1;
        }
        if (memo[r][c] != null) {
            return memo[r][c];
        }
        int paths = dfs(grid, r - 1, c, memo) + dfs(grid, r, c - 1, memo);

        memo[r][c] = paths;
        return paths;
    }
}
