import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];

        for (int i = 0; i < n; i++) {
            long val = nums[i];
            squares[i] = val * val;
        }

        // Sort squares in ascending order
        Arrays.sort(squares);

        int negativeCount = n / 2;

        long score = 0;

        // Subtract the smallest elements
        for (int i = 0; i < negativeCount; i++) {
            score -= squares[i];
        }

        // Add the largest elements
        for (int i = negativeCount; i < n; i++) {
            score += squares[i];
        }

        return score;
    }
}