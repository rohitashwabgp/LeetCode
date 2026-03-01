public class LeetCode215 {
    public int findKthLargest(int[] nums, int k) {
        return findPivot(nums, 0, nums.length - 1, nums.length - k);
    }


    public int findPivot(int[] nums, int left, int right, int k) {
        int pivot = pivot(nums, left, right);
        if (left <= right) {
            if (pivot == k) return nums[pivot];
            else if (pivot > k) return findPivot(nums, left, pivot - 1, k);
            else return findPivot(nums, pivot + 1, right, k);
        }
        return -1;
    }

    private int pivot(int[] nums, int left, int right) {
        int j = left;
        for (int i = left; i < right; i++) {
            if (nums[i] <= nums[right]) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
        int temp = nums[right];
        nums[right] = nums[j];
        nums[j] = temp;
        return j;
    }
}
