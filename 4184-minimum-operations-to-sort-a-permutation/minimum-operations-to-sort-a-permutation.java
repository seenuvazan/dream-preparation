class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }
        if (n == 2) {
            return (nums[0] == 0) ? 0 : 1;
        }

        int drops = 0;
        int climbs = 0;
        int idx0 = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx0 = i;
            }
            int next = nums[(i + 1) % n];
            if (nums[i] > next) {
                drops++;
            }
            if (nums[i] < next) {
                climbs++;
            }
        }

        // Case 1: Cyclically increasing
        if (drops <= 1) {
            if (idx0 == 0) {
                return 0;
            }
            return Math.min(idx0, n - idx0 + 2);
        }

        // Case 2: Cyclically decreasing
        if (climbs <= 1) {
            return Math.min(n - idx0, idx0 + 2);
        }

        return -1;
    }
}