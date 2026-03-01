import java.util.Stack;

public class LeetCode85 {
    static int count = 0;

    public static int maximalRectangle(char[][] matrix) {
        int[] heights = new int[matrix[0].length];
        int maxArea = 0;
        for (char[] chars : matrix) {
            for (int col = 0; col < chars.length; col++) {
                if (chars[col] == '1') {
                    heights[col]++;
                } else {
                    heights[col] = 0;
                }
            }
            maxArea = Math.max(calculateMaxArea(heights), maxArea);
        }
        return maxArea;

    }

    private static int calculateMaxArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }


}
