import data.TreeNode;

public class LeetCode96 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int nodes = 2; nodes <= n; nodes++) {
            for (int root = 1; root <= nodes; root++) {
                dp[nodes] = dp[nodes] + dp[root - 1] * dp[nodes - root];
            }
        }

        return dp[n];
    }

    public boolean isValidBST(TreeNode root) {
        return valid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean valid(TreeNode node, int min, int max) {
        if (node == null) return true;
        if (node.val < min || node.val > max) return false;
        return valid(node.left, min, node.val) && valid(node.right, node.val, max);
    }
}
