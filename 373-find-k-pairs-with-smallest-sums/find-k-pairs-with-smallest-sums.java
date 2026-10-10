import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k <= 0) {
            return result;
        }

        // Min-heap storing: [sum, i, j]
        // Compare sums; using long prevents potential 32-bit integer overflow
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> Long.compare((long) nums1[a[0]] + nums2[a[1]], (long) nums1[b[0]] + nums2[b[1]])
        );

        // Seed heap with (i, 0) for i in [0, min(nums1.length, k) - 1]
        int limit = Math.min(nums1.length, k);
        for (int i = 0; i < limit; i++) {
            minHeap.offer(new int[]{i, 0});
        }

        while (k > 0 && !minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int i = curr[0];
            int j = curr[1];

            result.add(Arrays.asList(nums1[i], nums2[j]));
            k--;

            // Advance to the next element in the same row
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{i, j + 1});
            }
        }

        return result;
    }
}