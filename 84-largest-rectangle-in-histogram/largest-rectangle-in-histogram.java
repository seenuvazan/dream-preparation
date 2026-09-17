import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int leftBound = stack.isEmpty() ? -1 : stack.peek();
                int width = i - leftBound - 1;
                maxArea = Math.max(maxArea, h * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}