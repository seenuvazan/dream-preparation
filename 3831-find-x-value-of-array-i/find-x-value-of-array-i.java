class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            int v = num % k;
            long[] newDp = new long[k];

            // Extend previously ending subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    newDp[(r * v) % k] += dp[r];
                }
            }

            // Start a new subarray with the current element
            newDp[v]++;

            // Accumulate counts into the result array
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            dp = newDp;
        }

        return result;
    }
}