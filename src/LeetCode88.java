public class LeetCode88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int lastIndexNum1 = m - 1;
        int lastIndexNum2 = n - 1;

        int lastIndex = m + n - 1;

        while (lastIndexNum1 >= 0 && lastIndexNum2 >= 0) {
            if (nums1[lastIndexNum1] > nums2[lastIndexNum2]) {
                nums1[lastIndex] = nums1[lastIndexNum1];
                lastIndexNum1--;
            } else {
                nums1[lastIndex] = nums2[lastIndexNum2];
                lastIndexNum2--;
            }
            lastIndex--;
        }
        while (lastIndexNum2 >= 0) {
            nums1[lastIndex] = nums2[lastIndexNum2];
            lastIndexNum2--;
            lastIndex--;
        }
    }
}
