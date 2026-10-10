import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long maxKelements(int[] nums, int k) {
        // Max-heap to repeatedly pick the largest element
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            maxHeap.offer(num);
        }

        long score = 0;

        while (k > 0 && !maxHeap.isEmpty()) {
            int val = maxHeap.poll();
            
            // If the maximum value is 1, all further operations contribute 1
            if (val == 1) {
                score += k;
                break;
            }

            score += val;
            maxHeap.offer((val + 2) / 3); // Equivalent to ceil(val / 3.0)
            k--;
        }

        return score;
    }
}