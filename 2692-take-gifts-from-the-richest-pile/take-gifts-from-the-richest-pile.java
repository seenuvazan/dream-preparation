import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long pickGifts(int[] gifts, int k) {
        // Max-heap to store pile sizes
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int gift : gifts) {
            maxHeap.offer(gift);
        }

        while (k > 0 && !maxHeap.isEmpty()) {
            int top = maxHeap.poll();
            if (top <= 1) {
                maxHeap.offer(top);
                break; // Further sqrt operations will not change the values
            }
            maxHeap.offer((int) Math.sqrt(top));
            k--;
        }

        // Sum up the remaining gifts
        long totalGifts = 0;
        while (!maxHeap.isEmpty()) {
            totalGifts += maxHeap.poll();
        }

        return totalGifts;
    }
}