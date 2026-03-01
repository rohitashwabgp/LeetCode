import java.util.Arrays;
import java.util.Stack;

public class LeetCode84 {

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int area = 0;
        int n = heights.length;
        int[] newHeights = Arrays.copyOf(heights, n + 1);
        for (int i = 0; i < newHeights.length; i++) {
            int h = newHeights[i];
            while (!stack.isEmpty() && newHeights[stack.peek()] >= h) {
                int height = stack.pop();
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                area = Math.max(area, height * width);

            }
            stack.push(i);
        }
        return area;
    }
}
