public class LeetCode11 {
    public int maxArea(int[] height) {
        int maxArea = Integer.MIN_VALUE;

        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int width = right - left;
            int maxHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, (maxHeight * width));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
