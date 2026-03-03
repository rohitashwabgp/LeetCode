public class LeetCode213 {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int endIndex = nums.length - 1;
        int startIndex = 0;
        int startCost = earned(nums, startIndex, endIndex - 1);
        int endCost = earned(nums, startIndex + 1, endIndex);
        return Math.max(startCost, endCost);
    }

    private int earned(int[] nums, int start, int end) {
        int skip = 0;
        int take = 0;
        for (int i = start; i <= end; i++) {
            int temp = take;
            take = Math.max(skip + nums[i], take);
            skip = temp;
        }
        return Math.max(take, skip);
    }
}
