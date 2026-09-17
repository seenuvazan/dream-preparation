import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);

        int left = 0;
        int currentSum = 0;
        int ans = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            currentSum += arr[right];

            while (currentSum > target && left <= right) {
                currentSum -= arr[left];
                left++;
            }

            if (right > 0) {
                minLen[right] = minLen[right - 1];
            }
            if (currentSum == target) {
                int currLen = right - left + 1;

                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, currLen + minLen[left - 1]);
                }
                minLen[right] = Math.min(minLen[right], currLen);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}