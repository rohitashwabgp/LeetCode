public class LeetCode3010 {

    public int minimumCost(int[] nums) {
        int n = nums.length;
        int base = nums[0];
        int minSecond = nums[1];
        int ans = Integer.MAX_VALUE;
        for (int j = 2; j < n; j++) {
            ans = Math.min(ans, base + minSecond + nums[j]);
            minSecond = Math.min(minSecond, nums[j]);
        }
        return ans;
    }

    public int minimumCost(int[] nums, int k, int dist) {
        int size = nums.length;
        if (k == 1) return nums[0];
        int[][] dp = new int[size][k + 1];

        for (int i = 0; i < size; i++)
            for (int j = 0; j <= k; j++)
                dp[i][j] = Integer.MAX_VALUE;

        dp[0][1] = nums[0];

        // Fill dp
        for (int count = 2; count <= k; count++) {
            for (int i = 1; i < size; i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j][count - 1] != Integer.MAX_VALUE) {
                        if (count == k && i - 1 - 1 > dist) continue;
                        dp[i][count] = Math.min(dp[i][count], dp[j][count - 1] + nums[i]);
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            ans = Math.min(ans, dp[i][k]);
        }
        return ans;
    }
}
