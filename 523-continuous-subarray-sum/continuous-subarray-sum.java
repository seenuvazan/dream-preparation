import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        // Map: remainder -> earliest index seen
        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1); // Base case for subarrays starting at index 0

        int runningSum = 0;

        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            int remainder = runningSum % k;

            if (remainderMap.containsKey(remainder)) {
                // Check if the subarray length is at least 2
                if (i - remainderMap.get(remainder) >= 2) {
                    return true;
                }
            } else {
                // Only store the first occurrence to maximize the subarray length
                remainderMap.put(remainder, i);
            }
        }

        return false;
    }
}