class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;
        
        // Find maximum difference to dimension the bucket array
        for (int i = 0; i < n; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(nums1[i] - nums2[i]));
        }
        
        if (maxDiff == 0) return 0;
        
        int[] count = new int[maxDiff + 1];
        for (int i = 0; i < n; i++) {
            count[Math.abs(nums1[i] - nums2[i])]++;
        }
        
        long k = (long) k1 + k2;
        
        // Greedily reduce largest differences
        for (int v = maxDiff; v > 0 && k > 0; v--) {
            if (count[v] == 0) continue;
            
            if (k >= count[v]) {
                k -= count[v];
                count[v - 1] += count[v];
                count[v] = 0;
            } else {
                count[v - 1] += (int) k;
                count[v] -= (int) k;
                k = 0;
            }
        }
        
        // Compute final sum of squared differences
        long ans = 0;
        for (int v = 1; v <= maxDiff; v++) {
            if (count[v] > 0) {
                ans += (long) count[v] * v * v;
            }
        }
        
        return ans;
    }
}