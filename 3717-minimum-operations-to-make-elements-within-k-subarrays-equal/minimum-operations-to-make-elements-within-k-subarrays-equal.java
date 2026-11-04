import java.util.*;

class Solution {
    static class SlidingMedianCost {
        private final TreeMap<Long, Integer> lower = new TreeMap<>();
        private final TreeMap<Long, Integer> upper = new TreeMap<>();
        private final int x;
        private int lowerSize = 0;
        private int upperSize = 0;
        private long sumLower = 0;
        private long sumUpper = 0;

        public SlidingMedianCost(int x) {
            this.x = x;
        }

        private void addKey(TreeMap<Long, Integer> map, long key) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        private void removeKey(TreeMap<Long, Integer> map, long key) {
            int count = map.get(key);
            if (count == 1) {
                map.remove(key);
            } else {
                map.put(key, count - 1);
            }
        }

        private void balance() {
            int targetLower = (x + 1) / 2;

            // Shift from lower to upper if lower has too many elements
            while (lowerSize > targetLower) {
                long maxLower = lower.lastKey();
                removeKey(lower, maxLower);
                sumLower -= maxLower;
                lowerSize--;

                addKey(upper, maxLower);
                sumUpper += maxLower;
                upperSize++;
            }

            // Shift from upper to lower if lower has too few elements
            while (lowerSize < targetLower && upperSize > 0) {
                long minUpper = upper.firstKey();
                removeKey(upper, minUpper);
                sumUpper -= minUpper;
                upperSize--;

                addKey(lower, minUpper);
                sumLower += minUpper;
                lowerSize++;
            }
        }

        public void add(long val) {
            if (lowerSize == 0 || val <= lower.lastKey()) {
                addKey(lower, val);
                sumLower += val;
                lowerSize++;
            } else {
                addKey(upper, val);
                sumUpper += val;
                upperSize++;
            }
            balance();
        }

        public void remove(long val) {
            if (lower.containsKey(val)) {
                removeKey(lower, val);
                sumLower -= val;
                lowerSize--;
            } else {
                removeKey(upper, val);
                sumUpper -= val;
                upperSize--;
            }
            balance();
        }

        public long getCost() {
            long median = lower.lastKey();
            long costLower = median * (long) lowerSize - sumLower;
            long costUpper = sumUpper - median * (long) upperSize;
            return costLower + costUpper;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        SlidingMedianCost window = new SlidingMedianCost(x);

        // Precompute cost for the first window
        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }
        cost[0] = window.getCost();

        // Slide the window
        for (int i = 1; i <= n - x; i++) {
            window.remove(nums[i - 1]);
            window.add(nums[i + x - 1]);
            cost[i] = window.getCost();
        }

        // DP to find minimum cost of selecting k non-overlapping intervals
        final long INF = (long) 1e18;
        long[] dp = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            long[] nextDp = new long[n + 1];
            Arrays.fill(nextDp, INF);

            for (int i = j * x; i <= n; i++) {
                // Option 1: Skip nums[i - 1]
                nextDp[i] = nextDp[i - 1];

                // Option 2: Pick interval nums[i - x ... i - 1]
                if (dp[i - x] != INF) {
                    nextDp[i] = Math.min(nextDp[i], dp[i - x] + cost[i - x]);
                }
            }
            dp = nextDp;
        }

        return dp[n];
    }
}