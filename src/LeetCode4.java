public class LeetCode4 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;

        if (size2 < size1) {
            findMedianSortedArrays(nums2, nums1);
        }

        int start = 0;
        int end = nums1.length - 1;

        while (start <= end) {
            int partitionX = (start + end) / 2;
            int partitionY = (size1 + size2 + 1) / 2 - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == size1) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == size2) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {

                if ((size1 + size2) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }

            } else if (maxLeftX > minRightY) {
                end = partitionX - 1;
            } else {
                start = partitionX + 1;
            }
        }
        throw new IllegalArgumentException();
    }


    public static void main(String[] args) {
        double median = findMedianSortedArrays(new int[]{}, new int[]{});
    }
}
