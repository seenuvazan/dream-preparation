class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        // count[r] stores how many times remainder r has been seen
        int[] count = new int[k];
        count[0] = 1; // Base case: an empty prefix has sum 0 (0 % k == 0)

        int prefixSum = 0;
        int result = 0;

        for (int num : nums) {
            prefixSum += num;
            
            // Normalize remainder to range [0, k - 1]
            int remainder = (prefixSum % k + k) % k;

            // Every previous occurrence of this remainder forms a valid subarray
            result += count[remainder];

            // Record this remainder
            count[remainder]++;
        }

        return result;
    }
}