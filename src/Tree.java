import data.TreeNode;

import java.util.*;

public class Tree {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int nodeIdx = 0; nodeIdx < size; nodeIdx++) {
                TreeNode next = queue.poll();
                if (nodeIdx == size - 1) {
                    result.add(next.val);
                }
                if (next.left != null) {
                    queue.add(next.left);
                }

                if (next.right != null) {
                    queue.add(next.right);
                }
            }
        }
        return result;
    }

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;

        // Paths starting from current node
        int pathsFromRoot = countPathFrom(root, targetSum);

        // Paths from left and right subtree
        int pathsFromLeft = pathSum(root.left, targetSum);
        int pathsFromRight = pathSum(root.right, targetSum);

        return pathsFromRoot + pathsFromLeft + pathsFromRight;
    }

    private int countPathFrom(TreeNode node, int target) {
        if (node == null) return 0;

        int count = 0;
        if (node.val == target) count++;  // path ends here

        // Continue downwards
        count += countPathFrom(node.left, target - node.val);
        count += countPathFrom(node.right, target - node.val);

        return count;
    }

    public int pathSumII(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0L, 1);  // base case
        return dfs(root, 0, targetSum, prefixSum);
    }

    private int dfs(TreeNode node, long currSum, int target, Map<Long, Integer> prefixSum) {
        if (node == null) return 0;

        currSum = currSum + node.val;
        int count = prefixSum.getOrDefault(currSum - target, 0);

        prefixSum.put(currSum, prefixSum.getOrDefault(currSum, 0) + 1);

        count = count + dfs(node.left, currSum, target, prefixSum);
        count = count + dfs(node.right, currSum, target, prefixSum);

        // backtrack
        prefixSum.put(currSum, prefixSum.get(currSum) - 1);

        return count;
    }
}
