import java.util.HashSet;
import java.util.Set;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 31L;
    private static final long BASE2 = 37L;

    public String longestDupSubstring(String s) {
        int n = s.length();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = s.charAt(i) - 'a';
        }

        int low = 1, high = n - 1;
        int bestStart = -1, bestLen = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int startIdx = search(mid, nums, n);

            if (startIdx != -1) {
                bestStart = startIdx;
                bestLen = mid;
                low = mid + 1; // Try longer length
            } else {
                high = mid - 1; // Try shorter length
            }
        }

        return bestLen == 0 ? "" : s.substring(bestStart, bestStart + bestLen);
    }

    // Returns the starting index of any duplicate substring of length L, or -1 if none exists
    private int search(int L, int[] nums, int n) {
        long h1 = 0, h2 = 0;
        long p1 = 1, p2 = 1;

        // Compute BASE^L mod MOD and the hash for the first window of size L
        for (int i = 0; i < L; i++) {
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;
            if (i > 0) {
                p1 = (p1 * BASE1) % MOD1;
                p2 = (p2 * BASE2) % MOD2;
            }
        }

        // Store 64-bit combined hash: (h1 << 32) | h2
        Set<Long> seen = new HashSet<>();
        seen.add((h1 << 32) | h2);

        // Slide the window across the string
        for (int start = 1; start <= n - L; start++) {
            // Remove outgoing char nums[start - 1] and add incoming char nums[start + L - 1]
            h1 = (h1 - nums[start - 1] * p1 % MOD1 + MOD1) % MOD1;
            h1 = (h1 * BASE1 + nums[start + L - 1]) % MOD1;

            h2 = (h2 - nums[start - 1] * p2 % MOD2 + MOD2) % MOD2;
            h2 = (h2 * BASE2 + nums[start + L - 1]) % MOD2;

            long combinedHash = (h1 << 32) | h2;
            if (!seen.add(combinedHash)) {
                return start; // Found a duplicate
            }
        }

        return -1;
    }
}