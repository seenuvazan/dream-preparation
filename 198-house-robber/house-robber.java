class Solution {
    public int rob(int[] nums) {
        int prev2 = 0; // Represents dp[i - 2]
        int prev1 = 0; // Represents dp[i - 1]

        for (int num : nums) {
            int current = Math.max(prev1, prev2 + num);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}